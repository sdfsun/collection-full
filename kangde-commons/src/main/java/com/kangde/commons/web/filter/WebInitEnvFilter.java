package com.kangde.commons.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * WEB 初始化环境Filter
 * 实现了绑定请求和响应到当前线程
 * @author lisuo
 *
 */
public class WebInitEnvFilter implements Filter {

	/** 用于存储请求以及响应的ThreadLocal */
	private static final ThreadLocal<ServletWebRequest> tl = new ThreadLocal<ServletWebRequest>();

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		tl.set(new ServletWebRequest(req, res));
		chain.doFilter(request, response);
		tl.remove();
	}

	public static ServletWebRequest getServletWebRequest() {
		return tl.get();
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
	}
}
