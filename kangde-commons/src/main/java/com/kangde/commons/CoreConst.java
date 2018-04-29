package com.kangde.commons;

import java.nio.charset.Charset;

import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * 核心常量类
 * @author lisuo
 *
 */
public abstract class CoreConst {
	
	/** Config.properties */
	private static PropertiesConfiguration proconfig;
	
	static{
		try {
			Resource resource = new ClassPathResource("config.properties");
			proconfig = new PropertiesConfiguration(resource.getFile());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/** UTF-8 */
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	/** 系统管理员登录名称 */
	public static final String SUPER_ADMIN_LOGIN_NAME = getString("system.admin.login_name");
	/** 系统管理员登录密码 */
	public static final String SYSTEM_ADMIN_PASSWORD = getString("system.admin.password");
	/** 系统管理员中文名称 */
	public static final String SYSTEM_ADMIN_NAME = getString("system.admin.name");
	/** 系统管理员登录密码盐 */
	public static final String SYSTEM_ADMIN_SALT = getString("system.admin.salt");
	/** 当前用户,Session key */
	public static final String CURRENT_USER = "CURRENT_USER";
	/** 定时跑批的用户名 */
	public static final String SYSTEM_TIMER_USER = "admin";
	
	
	// ---------------------系统状态配置Start---------------------------//
	/** 系统状态：正常/启用 */
	public static final Integer STATUS_NORMAL = 1;
	/** 系统状态：禁用/停用/ */
	public static final Integer STATUS_DELETE = 0;
	/** 系统状态：软删除 */
	public static final Integer STATUS_INVALID = -1;
	// ---------------------系统状态配置End---------------------------//
	
	/***
	 * 获取配置文件值
	 * @param key 配置文件中的key
	 * @return
	 */
	public static String getString(String key){
		return proconfig.getString(key);
	}
	
	/***
	 * 获取配置文件值数组形式
	 * @param key 配置文件中的key
	 * @return
	 */
	public static String[] getStringArray(String key){
		return proconfig.getStringArray(key);
	}
	
	/***
	 * 获取配置文件值
	 * @param key 配置文件中的key
	 * @return
	 */
	public static Integer getInteger(String key){
		return proconfig.getInteger(key,null);
	}
	
	/***
	 * 获取配置文件值
	 * @param key 配置文件中的key
	 * @return
	 */
	public static Boolean getBoolean(String key){
		return proconfig.getBoolean(key, null);
	}

	
}
