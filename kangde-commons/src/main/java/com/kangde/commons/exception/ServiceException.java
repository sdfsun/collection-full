package com.kangde.commons.exception;

/**
 * 服务异常
 * @author lisuo
 *
 */
public class ServiceException extends BaseException {

	private static final long serialVersionUID = 3554121115227963665L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	
}
