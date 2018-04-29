package com.kangde.collection.exception;

/**
 * 
  * @Description: 地址异常类
  * @author lidengwen
  * @date 2016年7月29日 下午2:00:48
  *
 */
public class AddressException extends Exception{

	private static final long serialVersionUID = -5113266937432649821L;

	public AddressException() {
		super();
	}

	public AddressException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AddressException(String message, Throwable cause) {
		super(message, cause);
	}

	public AddressException(String message) {
		super(message);
	}

	public AddressException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
