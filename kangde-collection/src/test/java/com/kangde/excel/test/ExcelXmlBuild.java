package com.kangde.excel.test;


import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.util.TypeUtils;

import com.kangde.collection.dto.CaseViewDto;
import com.kangde.commons.util.ReflectUtil;

/**
 * 蹇�熸瀯寤篍xcel閰嶇疆,鍒楀鏃舵晱鎹峰紑鍙戜娇鐢�
 * @author lisuo
 *
 */
public class ExcelXmlBuild {
	
	//鏄惁鐢熸垚鍏朵粬鏍囩
	private static boolean otherTag = false;
	
	//蹇�熸瀯寤轰竴涓猉ML閰嶇疆,鐪嬩笉鎳傜洿鎺ヨ繍琛�
	public static void main(String[] args) {
		String xml = builderXml("caseViewDto", "妗堜欢淇℃伅鍒楄〃", true, CaseViewDto.class,5000);
		System.out.println(xml);
	}
	
	/**
	 * 鍒涘缓Excel 鏍囩
	 * @param id 鏍囩ID
	 * @param sheetname 瀵煎嚭鏃秙heet鍚嶇О
	 * @param enableStyle 鏄惁寮�鍚牱寮忔敮鎸�
	 * @param clazz 鐢熸垚鐨勭被鍨�
	 * @param defaultColumnWidth 榛樿瀹藉害
	 * @param clazzs 
	 * 澶嶆潅绫诲瀷鏁扮粍(姣斿闇�瑕佺敓鎴恠tudent.book.name),
	 * 閭ｄ箞濡傛灉浼犲叆鐨刢lazz鏄疭tudent,
	 * 閭ｄ箞杩欓噷搴旇浼犻�払ook.class
	 * @return
	 */
	public static String builderXml(String id,String sheetname,boolean enableStyle,Class<?> clazz,Integer defaultColumnWidth,Class<?> ... clazzs){
		StringBuilder res = new StringBuilder();
		res.append("<excel id=\""+id+"\" class=\""+clazz.getName()+"\" sheetname=\""+sheetname+"\" defaultColumnWidth=\""+defaultColumnWidth+"\" enableStyle=\""+enableStyle+"\">");
		List<Field> fields = ReflectUtil.getFields(clazz);
		for(Field f:fields){
			String str = buildFields(f, f.getName(), clazzs);
			res.append(str);
		}
		res.append("\n</excel>");
		return res.toString();
	}
	
	/**
	 * 鐢熸垚field鏍囩
	 * @param field 瀛楁
	 * @param name 瀛楁鍚嶇О
	 * @param clazzs 澶嶆潅瀵硅薄绫诲瀷
	 * @return
	 */
	private static String buildFields(Field field, String name,Class<?> ... clazzs) {
		StringBuilder res = new StringBuilder();
		boolean build = true;
		if(ArrayUtils.isNotEmpty(clazzs)){
			for(Class<?> clz:clazzs){
				if(field.getType()==clz){
					build = false;
					List<Field> fs = ReflectUtil.getFields(clz);
					for(Field f:fs){
						res.append(buildFields(f, name+"."+f.getName(), clazzs));
					}
				}
			}
		}
		if(build){
			buildStringField(res, field, name);
		}
		return res.toString();
	}
	//鍒涘缓field鏍囩瀛楃
	private static void buildStringField(StringBuilder res,Field field, String name){
		//涓嶆槸澶嶆潅瀵硅薄
		res.append("\n")	
		.append("\t<field")
		.append(" name=").append("\""+name+"\"")
		.append(" title=").append("\""+name+"\"");
		res.append(" align=").append("\"left\"");
		res.append(" uniformStyle=").append("\"true\"");
		if(TypeUtils.isAssignable(Date.class, field.getType())){
			String pattern = "yyyy/MM/dd";
			res.append(" pattern=").append("\""+pattern+"\"");
		}
		//鏄惁鏋勫缓鍏朵粬鏍囩淇℃伅
		if(otherTag){
			//鏄惁鍏佽涓虹┖
			res.append(" isNull=").append("\""+true+"\"");
			//cell鏍峰紡鏄惁涓庢爣棰樹竴鑷�
			res.append(" uniformStyle=").append("\""+true+"\"");
			//cell瀹藉害
			res.append(" columnWidth=").append("\"5000\"");
			//瀵煎叆鏃舵鍒�
			res.append(" regex=").append("\"\"");
			//瀵煎叆鏃舵鍒�
			res.append(" regexErrMsg=").append("\""+name+"涓嶅悎娉昞");
			//format
			//resolveFieldValueConverterName
			//columnWidth
			//align
			//titleBgColor
			//titleFountColor
			//uniformStyle
			
		}
		res.append("/>");
	}
	
}
