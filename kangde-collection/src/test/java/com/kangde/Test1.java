package com.kangde;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.kangde.collection.model.CaseModel;
import com.kangde.commons.util.UUIDUtil;

public class Test1 {
	public static void main(String[] args) {
		Set<String> ids = new HashSet<>();
		for(int i=0;i<100000;i++){
			ids.add(UUIDUtil.UUID32());
		}
		System.out.println(ids.size());
	}
	
	public static void t(List<? extends CaseModel > ls){}
	
	/**
	 * 隐藏中间四位
	 * @param str
	 * @return
	 */
	private static String middleFourHide(String str){
		if(str.length() > 5){
			int idx = str.length() / 2;
			String prefix = str.substring(0,idx-2)+"****";
			String subfix = str.substring(idx+2,str.length());
			return prefix+subfix;
		}
		return str;
	}
	
	/**
	 * 隐藏后四位
	 * @param str
	 * @return
	 */
	public static String lastFourHide(String str){
		if(str.length() > 4){
			String subfix = str.substring(str.length() - 4);
			String prefix = str.substring(0, str.lastIndexOf(subfix));
			return prefix+"****";
		}
		return str;
	}
	
}
