package com.kangde.excel.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.kangde.commons.util.excel.ExcelContext;
import com.kangde.commons.util.excel.vo.ExcelImportResult;
/**
 * Excel导入测试
 * @author lisuo
 *
 */
public class ImportTest {
	
	// 测试时文件磁盘路径
	private static String path = "C:/Users/Administrator/Desktop/stu.xlsx";
	// 配置文件路径
	private static ExcelContext context = new ExcelContext("otherConfig/excel-config.xml");
	// Excel配置文件中配置的id
	private static String excelId = "student";
	
	/**
	 * 导入Excel
	 * @throws Exception
	 */
	@Test
	public void testImport()throws Exception{
		InputStream fis = new FileInputStream(path);
		//第二个参数需要注意,它是指标题索引的位置,可能你的前几行并不是标题,而是其他信息,
		//比如数据批次号之类的,关于如何转换成javaBean,具体参考配置信息描述
		ExcelImportResult result = context.readExcel(excelId, 2, fis);
		System.out.println(result.getHeader());
		System.out.println(result.getListBean());
		
		//这种方式和上面的没有任何区别,底层方法默认标题索引为0
		//context.readExcel(excelId, fis);
	}
	
	
		
}
