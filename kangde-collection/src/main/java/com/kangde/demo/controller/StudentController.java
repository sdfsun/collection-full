package com.kangde.demo.controller;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.commons.easyui.Column;
import com.kangde.commons.easyui.Datagrid;
import com.kangde.commons.util.CcUtil;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.util.excel.ExcelHeader;
import com.kangde.commons.util.excel.vo.ExcelDefinition;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.demo.model.StudentModel;

/**
 * 测试学生Controller
 * @author lisuo
 *
 */
@Controller
@RequestMapping("demo/student")
public class StudentController extends RestfulUrlController<StudentModel,String>{
	
	//查询
	@Override
	public SearchResult<StudentModel> queryForPage() {
		ParamCondition condition = parseCondition("name","age");
		return super.queryForPage(condition);
	}
	
	@ResponseBody
	@RequestMapping(value = "pageQuery", method = RequestMethod.GET)
	//动态列分页查询
	public Datagrid<StudentModel> queryForDynamicColomnPage(){
		ParamCondition condition = parseCondition("name","age");
		SearchResult<StudentModel> students = super.queryForPage(condition);
		Datagrid<StudentModel> dg = new Datagrid<>();
		Column nameColumn = new Column("name", "姓名", 200, Column.ALIGN_LEFT);
		Column ageColumn = new Column("age", "年龄", 200, Column.ALIGN_LEFT);
		dg.addColumn(nameColumn);
		dg.addColumn(ageColumn);
		dg.setTotal(students.getTotal());
		dg.setRows(students.getRows());
		return dg;
		
	}
	
	//导出Excel
	@RequestMapping(value="/exportExcel",method=RequestMethod.GET)
	public ModelAndView exportExcel(){
		SearchResult<StudentModel> query = queryForPage();
		return super.createExcelView("student", query.getRows(), "测试学生信息" ,new ExcelHeader() {
			@Override
			public void buildHeader(Sheet sheet, ExcelDefinition excelDefinition, List<?> beans) {
				Row row = sheet.createRow(0);
				Row r2 = sheet.createRow(1);
				row.createCell(0).setCellValue("总记录数:"+beans.size());
				r2.createCell(0).setCellValue("创建时间:");
				r2.createCell(1).setCellValue(DateUtil.thisDateTime());
			}
		},null);
	}
	
	//重写排序两种形式,1在构造覆盖key
	public StudentController() {
		columnNames.put("studentNo", "student_no");
	}
	
	@Override
	public String getColumnName(String sortTxt) {
		//在这里判断
//		if("studentNo".equals(sortTxt)){
//			return "student_no";
//		}
		return super.getColumnName(sortTxt);
	}
	
	@Override
	protected String getBaseName() {
		return "demo/student/";
	}
}
