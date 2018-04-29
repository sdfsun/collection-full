package com.kangde.commons.web.view;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import com.kangde.commons.util.WEBUtil;
import com.kangde.commons.util.excel.ExcelContext;
import com.kangde.commons.util.excel.ExcelHeader;

/**
 * Excel视图解析器
 * @author lisuo
 *
 */
@Component
public class ExcelView extends AbstractView{
	
	/** Excel 名称 */
	public static final String EXCEL_NAME = "Excel.excelName";
	/** Excel ID */
	public static final String EXCEL_ID = "Excel.id";
	/** Excel 数据名称 */
	public static String EXCEL_BEANS = "Excel.beans";
	/** Excel 头信息名称 */
	public static String EXCEL_HEADER = "Excel.header";
	/** Excel 动态字段信息 */
	public static String EXCEL_FIELDS = "Excel.fields";
	
	/** 文件后缀 */
	public static final String FILE_SUFFIX = ".xlsx";
	
	/** 注入Excel上下文 */
	@Autowired
	private ExcelContext excelContext;
	
	@Override
	public void setContentType(String contentType) {
		super.setContentType("application/vnd.ms-excel");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String excelName = MapUtils.getString(model, EXCEL_NAME);
		String id = MapUtils.getString(model, EXCEL_ID);
		List<?> beans = (List<?>) model.get(EXCEL_BEANS);
		Object header = model.get(EXCEL_HEADER);
		Object fields = model.get(EXCEL_FIELDS);
		@SuppressWarnings("unchecked")
		Workbook workbook = excelContext.createExcel(id, beans,header==null?null:(ExcelHeader)header,fields ==null?null:(List<String>)fields);
		if(workbook!=null){
			excelName = WEBUtil.encodeDownloadFileName(request,excelName+FILE_SUFFIX);
			response.setContentType(getContentType());
			response.setHeader("Content-Disposition", "attachment; filename=\"" + excelName + "\";target=_blank");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			out.close();
		}else{
			response.setContentType("text/html; charset=utf-8");
			PrintWriter writer = response.getWriter();
			writer.print("<script language='javascript'>"
					+ "alert('没有可以导出的数据');"
					+ "window.location = "+request.getContextPath()+"/"
					+ "</script>");
			writer.flush();
			writer.close();
		}
	}
	
}
