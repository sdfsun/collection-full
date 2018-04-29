package com.kangde.collection.exception;

/**
 * 
  * @Description: 附件异常类
  * @author lidengwen
  * @date 2016年7月29日 下午2:00:48
  *
 */
public class AttachmentException extends Exception{

	private static final long serialVersionUID = -5113266937432649821L;

	public AttachmentException() {
		super();
	}

	public AttachmentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public AttachmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public AttachmentException(String message) {
		super(message);
	}

	public AttachmentException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
