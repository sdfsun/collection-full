package com.kangde.commons.exception.dao;

import com.kangde.commons.exception.BaseException;

/**
 * 乐观所异常
 * @author lisuo
 *
 */
public class OptimisticLockingFailureException extends BaseException{

	private static final long serialVersionUID = 1367404114719085106L;

	public OptimisticLockingFailureException() {
		super();
	}

	public OptimisticLockingFailureException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OptimisticLockingFailureException(String message, Throwable cause) {
		super(message, cause);
	}

	public OptimisticLockingFailureException(String message) {
		super(message);
	}

	public OptimisticLockingFailureException(Throwable cause) {
		super(cause);
	}
	
	
	
}
