package com.kangde.sys.security.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * MD5 生成工具类
 * @author lisuo
 *
 */
public class MD5Util {
	
	/** MD5迭代次数 */
	private static final Integer  hashIterations = 1;
	
	/**
	 * MD5生成,长度为32位
	 * @param source 字符明文
	 * @param salt 盐（佐料）
	 * @return
	 */
	public static String md5(String source,String salt){
		return new Md5Hash(source,salt, hashIterations).toString();
	}
	
	/**
	 * MD5生成,长度为32位
	 * @param source 字符明文
	 * @return
	 */
	public static String md5(String source){
		return new Md5Hash(source).toString();
	}
	
}
