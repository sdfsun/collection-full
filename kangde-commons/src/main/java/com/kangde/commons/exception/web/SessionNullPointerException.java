package com.kangde.commons.exception.web;

import com.kangde.commons.exception.BaseException;

/**
 * Session空指针异常
 * @author lisuo
 *
 */
public class SessionNullPointerException extends BaseException {

	private static final long serialVersionUID = -2284002546939849301L;

	public SessionNullPointerException() {
		super();
	}

	public SessionNullPointerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SessionNullPointerException(String message, Throwable cause) {
		super(message, cause);
	}

	public SessionNullPointerException(String message) {
		super(message);
	}

	public SessionNullPointerException(Throwable cause) {
		super(cause);
	}

}
