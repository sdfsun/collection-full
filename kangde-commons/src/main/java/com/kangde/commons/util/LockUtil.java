package com.kangde.commons.util;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;

import com.google.common.util.concurrent.Striped;

/**
 * 并发锁工具类
 * @author lisuo
 *
 */
public abstract class LockUtil {
	
	/** 创建一个弱引用的Striped<Lock>,缓存大小128 */
	private static final Striped<Lock> striped = Striped.lazyWeakLock(128);	
	
	/**
	 * 获取锁
	 * @param lockKey
	 * @return Lock
	 */
	public static Lock get(Serializable lockKey) {
		return striped.get(lockKey);//获取锁
	}
}
