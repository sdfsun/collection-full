package com.kangde.collection.view;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.kangde.collection.dto.VisitShowView;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.util.ReflectUtil;
import com.kangde.commons.util.WEBUtil;

/**
 * 外访模板doc视图生成
 * 
 * @author lisuo
 *
 */
@Component
public class VisitDocView extends AbstractView{
	
	/** Doc名称 */
	public static final String DOC_NAME = "VisitDocView.name";
	/** Doc内容 */
	public static final String DOC_CONTENT = "VisitDocView.content";
	/** 外访数据名称 */
	public static String VISIT_BEANS = "VisitDocView.beans";
	
	/** 文件后缀 */
	public static final String FILE_SUFFIX = ".doc";
	
	@Override
	public void setContentType(String contentType) {
		super.setContentType("application/msword");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String docName = MapUtils.getString(model, DOC_NAME);
		String content = MapUtils.getString(model, DOC_CONTENT);
		List<VisitShowView> records = (List<VisitShowView>) model.get(VISIT_BEANS);
		if(CollectionUtils.isNotEmpty(records)){
			docName = WEBUtil.encodeDownloadFileName(request,docName+FILE_SUFFIX);
			response.setContentType(getContentType());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + docName + "\";target=_blank");
			OutputStream out = response.getOutputStream();
			visitHtmlToWord2(content, records, out);
		}else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print("<script language='javascript'>"
					+ "alert('没有可以生成的数据');"
					+ "window.location = "+request.getContextPath()+"/"
					+ "</script>");
			writer.flush();
			writer.close();
		}
	}
	
	private static void visitHtmlToWord2(String content, List<VisitShowView> records,OutputStream os) throws Exception {
		String result = replace(content, records);
		// 拼一个标准的HTML格式文档
		String html = "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"/></head><body>"+ result + "</body></html>";
		InputStream is = new ByteArrayInputStream(html.getBytes("UTF-8"));
		inputStreamToWord(is, os);
	}

	// 替换字符
	private static String replace(String body, List<VisitShowView> records) {
		StringBuilder bui = new StringBuilder();
		for (VisitShowView record : records) {
			List<String> fieldNames = ReflectUtil.getFieldNames(VisitShowView.class);
			String temp = body;
			for (String fieldName : fieldNames) {
				Object value = ReflectUtil.getProperty(record, fieldName);
				if(value != null){
					if("caseDate".equals(fieldName) || "actualEndDate".equals(fieldName) 
							|| "overdueDate".equals(fieldName) || "loanStartdate".equals(fieldName)){
						value = DateUtil.date2Str((Date)value, DateUtil.DEFAULT_DATE_FORMAT);
					}else if("applyTime".equals(fieldName)){
						value = DateUtil.date2Str((Date)value, DateUtil.DEFAULT_DATE_TIME_FORMAT);
					}else if("caseMoney".equals(fieldName)){
						DecimalFormat df = new DecimalFormat("###,##0.00");
						df.setRoundingMode(RoundingMode.DOWN);
						ConvertUtils.convert(value, BigDecimal.class);
						
						value =df.format(value);
					}
				}
				temp = temp.replace("${" + fieldName + "}", value == null ? "" : value.toString());
			}
			bui.append(temp);
		}
		return bui.toString();
	}

	/**
	 * 把is写入到对应的word输出流os中
	 * 
	 * @param is
	 * @param os
	 * @throws IOException
	 */
	private static void inputStreamToWord(InputStream is, OutputStream os) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem();
		fs.createDocument(is, "WordDocument");
		fs.writeFilesystem(os);
		os.flush();
		os.close();
		is.close();
		fs.close();
	}
	
}
