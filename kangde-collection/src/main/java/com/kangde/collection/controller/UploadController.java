package com.kangde.collection.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.kangde.collection.FileTypeConst;
import com.kangde.collection.constant.PermissionConst;
import com.kangde.collection.exception.AttachmentException;
import com.kangde.collection.model.AttachmentModel;
import com.kangde.collection.service.AttachmentService;
import com.kangde.collection.util.PluploadUtil;
import com.kangde.commons.exception.ServiceException;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.web.controller.SimpleController;

@Controller
@RequestMapping("collection/upload")
public class UploadController extends SimpleController {
	private static Logger logger = Logger.getLogger(UploadController.class);

	/** 对附件的权限： 上传和删除：EDIT、 查看：VIEW */
//private static final List<String> actionList = Lists.newArrayList("edit", "view");
	@Autowired
	private AttachmentService attachmentService;
	private List<String> fileTypeList = Lists.newArrayList(FileTypeConst.IMAGE, FileTypeConst.PDF, FileTypeConst.WORD,
			FileTypeConst.EXCEL, FileTypeConst.AUDIO, FileTypeConst.VIDEO, FileTypeConst.ZIP, FileTypeConst.OTHER);

	@RequestMapping(value = PAGE_INDEX, method = RequestMethod.GET)
	public ModelAndView pageIndex() {
		ModelAndView view = new ModelAndView("collection/upload/index");
		String businessType = this.getRequest().getParameter("businessType");
		checkBusinessType(businessType);
		String action = this.getRequest().getParameter("action");
		view.addObject("businessType", businessType);
		view.addObject("action", action);
		String parameter = this.getRequest().getParameter("businessId");
		if ("".equals(parameter) || "undefined".equals(parameter) || "null".equals(parameter) || null == parameter) {
			throw new ServiceException("非法参数");
		}
		view.addObject("businessId", parameter);
		return view;
	}

	private void checkBusinessType(String type) {
		if (!PluploadUtil.typeList.contains(type)) {
			throw new RuntimeException("无效的资料类型");
		}
	}

	private boolean hasFileType(String fileType) {
		return fileTypeList.contains(fileType);
	}

	@RequestMapping(value = "/execute")
	@ResponseBody
	public String execute(HttpServletRequest request) throws IOException {
		PluploadUtil.upload(request);
		return new JSONObject().toJSONString();
	}

	/**
	 * 
	 * @Title: queryAttachmentsByCaseId
	 * @Description: 显示顺序为 图片、pdf、 word、 excel、音频、 视频
	 */
	@RequestMapping(value = "/queryAttachmentsByBusinessId")
	@ResponseBody
	public String queryAttachmentsByBusinessId(String businessId, HttpServletRequest request) {
		JSONObject re = new JSONObject();
		Map<String, List<AttachmentModel>> map = Maps.newLinkedHashMap();
		for (String type : fileTypeList) {
			map.put(type, Lists.newArrayList());// 按顺序显示
		}
		List<AttachmentModel> attachList = attachmentService.queryAttachmentsByCaseId(businessId);
		for (AttachmentModel caseAttachment : attachList) {
			setAttachmentType(caseAttachment);
			List<AttachmentModel> list = map.get(caseAttachment.getFileType());
			list.add(caseAttachment);
		}

		re.put("state", "true");
		re.put("msg", "加载成功");
		JSONObject json = new JSONObject();
		json.put("businessId", businessId);
		json.put("docNum", attachList.size());
		json.put("docIp", "http://" + PluploadUtil.docIp);
		json.put("attach", map);
		re.put("data", json);
		return re.toJSONString();
	}

	private void setAttachmentType(AttachmentModel caseAttachment) {
		String fileType = caseAttachment.getFileType();
		String path = PluploadUtil.getRoot() + caseAttachment.getPath();
		if (StringUtils.isBlank(fileType)) { // 没有文件类型
			try {
				fileType = PluploadUtil.generateFileType(new File(path));
				caseAttachment.setFileType(fileType);
				;
			} catch (AttachmentException e) {
				logger.error("获取文件类型异常, file:" + path + " msg:" + e.getMessage());
				caseAttachment.setName(caseAttachment.getName() + "【" + e.getMessage() + "】");
				caseAttachment.setFileType(FileTypeConst.OTHER);
			}
		} else { // 存在文件类型
			if (this.hasFileType(fileType)) {
				caseAttachment.setFileType(fileType);
			} else {
				caseAttachment.setFileType(FileTypeConst.OTHER);
			}
		}
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public String del(HttpServletRequest request) throws Exception {
		JSONArray ja = new JSONArray();
		attachmentService.mulDelete(request.getParameterValues("ids[]"));
		return ja.toJSONString();
	}

	@RequestMapping(value = "/queryCount")
	@ResponseBody
	public int queryCount(HttpServletRequest request) throws Exception {
		ParamCondition condition = new ParamCondition();
		condition.put("type", request.getParameter("type"));
		condition.put("businessId", request.getParameter("businessId"));
		int queryCount = attachmentService.queryCount(condition);
		return queryCount;
	}
}