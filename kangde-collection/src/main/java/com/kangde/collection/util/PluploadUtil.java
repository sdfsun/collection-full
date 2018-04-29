package com.kangde.collection.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.tika.Tika;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.google.common.collect.Lists;
import com.kangde.collection.FileTypeConst;
import com.kangde.collection.exception.AttachmentException;
import com.kangde.collection.model.AttachmentModel;
import com.kangde.collection.service.AttachmentService;
import com.kangde.commons.CoreConst;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.web.filter.SpringUtil;

/**
 * Plupload上传插件 上传原理为单个文件依次发送至服务器 上传打文件时可以将其碎片化上传
 */
public class PluploadUtil {
	private static final int BUF_SIZE = 2 * 1024;
	private static Logger logger = Logger.getLogger(PluploadUtil.class);
	static Tika tika = new Tika();
	/** 附件类型 案件：bankCase、cp凭证：cp、信函：mes、外访：vis、 批次导入batchImport */
	public static final List<String> typeList = Lists.newArrayList("bankCase", "cp", "mes", "vis", "batchImport");

	/**
	 * 文件根目录
	 */
	public static String root;
	/**
	 * 文档服务器IP
	 */
	public static String docIp;

	static {
		root = getRoot();
		docIp = getDocIp();
	}

	public static String getDocIp() {
		// baseUrl不配置， 则认为附件服务器和应用服务器在一台服务器
		String docIp = CoreConst.getString("docIp");
		if (StringUtils.isEmpty(docIp)) {
			return getLocalIp();
		}
		return docIp;
	}

	public static String getRoot() {
		String root = CoreConst.getString("rootDir");
		if (StringUtils.isEmpty(root)) {
			return "c:/upload";
		}
		return root;
	}

	private static String getLocalIp() {
		String localIp = "";
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			localIp = address.getHostAddress();
		} catch (Exception e) {
			logger.error("获取本机IP异常:" + e.getMessage());
		}
		return localIp;
	}

	public static AttachmentModel upload(HttpServletRequest request) throws IllegalStateException, IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultiValueMap<String, MultipartFile> map = multipartRequest.getMultiFileMap();
		String ck = request.getParameter("chunk");
		String cks = request.getParameter("chunks");

		int chunk = 0;
		int chunks = 1;
		if (StringUtils.isNotEmpty(ck) && StringUtils.isNotEmpty(cks)) {
			chunk = Integer.parseInt(ck);
			chunks = Integer.parseInt(cks);
		}

		AttachmentModel attachment = new AttachmentModel();
		String uuid32 = UUIDUtil.UUID32();
		attachment.setId(uuid32);
		String businessId = request.getParameter("businessId") == null ? (String) request.getAttribute("businessId")
				: request.getParameter("businessId");
		String businessType = request.getParameter("businessType") == null
				? (String) request.getAttribute("businessType") : request.getParameter("businessType");
		if (StringUtils.isEmpty(businessId) || StringUtils.isEmpty(businessType)) {
			throw new IllegalStateException("上传参数有误");
		}
		attachment.setFkId(businessId);

		checkBusinessType(businessType);
		attachment.setType(businessType);
		attachment.setIsjunk("1");

		if (map != null) {

			// 事实上迭代器中只存在一个值,所以只需要返回一个值即可
			Iterator<String> iter = map.keySet().iterator();
			while (iter.hasNext()) {
				String str = (String) iter.next();
				List<MultipartFile> fileList = map.get(str);
				for (MultipartFile multipartFile : fileList) {

					String name = request.getParameter("name");
					if (StringUtils.isEmpty(name)) {
						name = multipartFile.getOriginalFilename();
					}
					attachment.setName(name);
					String fileFormat = "." + name.substring(name.lastIndexOf(".") + 1);
					attachment.setFileFormat(fileFormat);

					String dir = generateDir(businessId);
					attachment.setPath(generateFilePath(dir, uuid32, fileFormat));

					// 创建新目标文件
					String fullPath = PluploadUtil.getRoot() + attachment.getPath();
					File targetFile = new File(fullPath);
					// 当chunks>1则说明当前传的文件为一块碎片，需要合并
					if (chunks > 1) {
						// 需要创建临时文件名，最后再更改名称
						File tempFile = new File(PluploadUtil.getRoot() + dir + multipartFile.getName());
						// 如果chunk==0,则代表第一块碎片,不需要合并
						saveUploadFile(multipartFile.getInputStream(), tempFile, chunk == 0 ? false : true);

						// 上传并合并完成，则将临时名称更改为指定名称
						if (chunks - chunk == 1) {
							logger.info("upload finished(merge)!" + attachment.getName());
							tempFile.renameTo(targetFile);
							setAttachType(attachment, targetFile);
							attachment.setSize(FileUtils.sizeOf(targetFile));
							AttachmentService bean = SpringUtil.getBean(AttachmentService.class);
							bean.save(attachment);
						}

					} else {
						// 否则直接将文件内容拷贝至新文件
						multipartFile.transferTo(targetFile);
						// 获取文件类型
						logger.info("upload finished(once)!" + attachment.getName());
						// 获取文件类型
						setAttachType(attachment, targetFile);
						attachment.setSize(FileUtils.sizeOf(targetFile));
						AttachmentService bean = SpringUtil.getBean(AttachmentService.class);
						bean.save(attachment);
					}

				}
			}
		}
		return attachment;

	}

	private static void setAttachType(AttachmentModel attachment, File targetFile) {
		try {
			String fileType = generateFileType(targetFile);
			attachment.setFileType(fileType);
		} catch (AttachmentException e) {
			logger.error("获取文件类型异常:filepath:" + targetFile.getPath() + " message:" + e.getMessage());
		}
	}

	public static String generateFileType(File file) throws AttachmentException {
		if (!file.exists()) {
			throw new AttachmentException("文件不存在");
		}
		try {
			String mineType = tika.detect(file);
			logger.info(file.getAbsolutePath() + "----->mine:" + mineType);
			String upperMineType = mineType.toUpperCase();
			if (upperMineType.startsWith(FileTypeConst.IMAGE)) {
				return FileTypeConst.IMAGE;
			}
			if (upperMineType.startsWith(FileTypeConst.AUDIO)) {
				return FileTypeConst.AUDIO;
			}
			if (upperMineType.startsWith(FileTypeConst.VIDEO)) {
				return FileTypeConst.VIDEO;
			}
			if (upperMineType.startsWith("APPLICATION")) {

				if (upperMineType.contains(FileTypeConst.PDF)) {
					return FileTypeConst.PDF;
				}

				if (upperMineType.contains(FileTypeConst.WORD)) {
					return FileTypeConst.WORD;
				}
				if (upperMineType.contains(FileTypeConst.EXCEL) || upperMineType.contains("SPREADSHEETML")) {
					return FileTypeConst.EXCEL;
				}

				if (upperMineType.contains("RAR") || upperMineType.contains("ZIP")) {
					return FileTypeConst.ZIP;
				}
			}
			return FileTypeConst.OTHER;
		} catch (IOException e) {
			logger.error("获取文件类型异常:" + e.getMessage());
			return FileTypeConst.OTHER;
		}
	}

	public static String generateDir(String caseId) {
		String dir = new SimpleDateFormat("/yyyy/MM/dd").format(new Date()) + "/" + caseId + "/";
		File fileDir = new File(PluploadUtil.getRoot() + dir);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return dir;
	}

	private static String generateFilePath(String dir, String uuid, String fileFormat) {
		return dir + uuid + fileFormat;
	}

	private static void checkBusinessType(String type) {
		if (!typeList.contains(type)) {
			throw new RuntimeException("无效的资料类型");
		}
	}

	public static void deleteFile(String path) {
		try {
			File file = new File(path);
			if (file.exists()) {
				FileUtils.forceDelete(file);
			}
		} catch (IOException e) {
			logger.error("删除文件失败:" + path + ":" + e.getMessage());
		}
	}

	/**
	 * 保存上传文件，兼合并功能
	 */
	private static void saveUploadFile(InputStream input, File targetFile, boolean append) throws IOException {
		OutputStream out = null;
		try {
			if (targetFile.exists() && append) {
				out = new BufferedOutputStream(new FileOutputStream(targetFile, true), BUF_SIZE);
			} else {
				out = new BufferedOutputStream(new FileOutputStream(targetFile), BUF_SIZE);
			}

			byte[] buffer = new byte[BUF_SIZE];
			int len = 0;
			// 写入文件
			while ((len = input.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			// 关闭输入输出流
			if (null != input) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 判断是否全部上传完成 碎片需合并后才返回真
	 */
	public static boolean isUploadFinish(int chunks, int chunk) {
		return (chunks - chunk) == 1;
	}
}
