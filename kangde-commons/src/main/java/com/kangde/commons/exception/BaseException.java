package com.kangde.commons.exception;

/**
 * 自定义异常基类
 * 用于与其他异常区分开来
 * @author lisuo
 *
 */
public class BaseException extends RuntimeException{

	private static final long serialVersionUID = -249569887168206843L;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
	
}
