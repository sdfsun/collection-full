package com.kangde.collection.exception;

/**
 * 
  * @Description: 节假日异常类
  * @author lidengwen
  *
 */
public class HolidayException extends Exception{

	
	private static final long serialVersionUID = -7726005354931257005L;

	public HolidayException() {
		super();
	}

	public HolidayException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public HolidayException(String message, Throwable cause) {
		super(message, cause);
	}

	public HolidayException(String message) {
		super(message);
	}

	public HolidayException(Throwable cause) {
		super(cause);
	}

}
