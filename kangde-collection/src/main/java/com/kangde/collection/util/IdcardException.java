package com.kangde.collection.util;

/**
 * 自定义身份证异常
 */
public class IdcardException extends Exception{
	private static final long serialVersionUID = 971726746591551899L;
	public IdcardException() {
		super();
	}

	public IdcardException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IdcardException(String message, Throwable cause) {
		super(message, cause);
	}

	public IdcardException(String message) {
		super(message);
	}

	public IdcardException(Throwable cause) {
		super(cause);
	}

}