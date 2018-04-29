package com.kangde.commons.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kangde.commons.exception.web.SessionNullPointerException;
import com.kangde.commons.web.filter.WebInitEnvFilter;

/**
 * WEB工具类
 * @author lisuo
 *
 */
@SuppressWarnings("unchecked")
public abstract class WEBUtil{
	
	/** 文件编码 */
	public static final String UTF8 = "UTF-8";
	/** 用户浏览器关键字：IE */
	private static final String USER_AGENT_IE = "MSIE";

	/**
	 * 获取请求
	 * 
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return WebInitEnvFilter.getServletWebRequest().getRequest();
	}

	/**
	 * 获取响应
	 * 
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		return WebInitEnvFilter.getServletWebRequest().getResponse();
	}

	/**
	 * 获取Session
	 * @return
	 * @throws SessionNullPointerException
	 */
	public static HttpSession getSession()throws SessionNullPointerException{
		HttpSession session = getRequest().getSession();
		if(session==null){
			throw new SessionNullPointerException("会话已过期");
		}
		return session;
	}

	/**
	 * 设置Session属性
	 * @param name
	 * @param value
	 */
	public static void setSessionAttribute(String name, Object value) {
		getSession().setAttribute(name, value);
	}

	/**
	 * 获取Session属性
	 * 
	 * @param name
	 * @return <T>
	 */
	public static <T> T getSessionAttribute(String name) {
		return (T) getSession().getAttribute(name);
	}

	/**
	 * 判断请求是否是Ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("X-Requested-With");
		if (header != null && "XMLHttpRequest".equals(header)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 根据不同的浏览器设置下载文件名称的编码
	 * @param request
	 * @param fileName
	 * @return 文件名称
	 */
	public static String encodeDownloadFileName(HttpServletRequest request,String fileName){
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.indexOf(USER_AGENT_IE)>0){//用户在用IE
			try {
				return URLEncoder.encode(fileName, UTF8);
			} catch (UnsupportedEncodingException ignore) {}
		}else{
			try {
				return new String(fileName.getBytes(UTF8), "ISO-8859-1");
			} catch (UnsupportedEncodingException ignore) {}
		}
		return fileName;
	}
	
}
