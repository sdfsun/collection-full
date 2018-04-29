package com.kangde.commons.util;

import java.io.InputStream;

/**
 * 上传文件工具类
 * @author lisuo
 *
 */
public abstract class UploadUtil {
	
	/**
	 * 上传文件到指定的文件夹
	 * @param path 文件路径
	 * @param filename 文件名称
	 * @param input 文件输入流
	 */
	public static void uploadFile(String path, String filename, InputStream input){
		System.out.println("上传成功");
	}

	/**
	 * 删除指定文件夹下的文件
	 * @param path 文件路径
	 * @param filename 文件名称
	 */
	public static void deleteFile(String caseExcelPath, String fileName) {
		System.out.println("删除成功");
	}
}
