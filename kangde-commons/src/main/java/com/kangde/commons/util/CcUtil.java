package com.kangde.commons.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.kangde.commons.web.filter.SpringUtil;

/**
 * 并发执行工具类
 * @author lisuo
 *
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public abstract class CcUtil {
	
	/** 线程池实例,Spring容器获取 */
	private static final ThreadPoolTaskExecutor THREAD_POOL = SpringUtil.getBean("threadPool");
	
	/**
	 * 异步执行
	 * @param tasks
	 * @return
	 */
	public static Collection<Future> asyncCall(Collection<? extends Callable> tasks){
		ExecutorService es = THREAD_POOL.getThreadPoolExecutor();
		List<Future> results = new ArrayList<Future>();
		for(Callable task : tasks){
			results.add(es.submit(task));
		}
		return results;
	}
	
	/**
	 * 异步执行
	 * @param task
	 * @return
	 */
	public static Future asyncCall(Callable task){
		ExecutorService es = THREAD_POOL.getThreadPoolExecutor();
		Future result = es.submit(task);
		return result;
	}

	/**
	 * 异步执行
	 * @param tasks
	 */
	public static void asyncRun(Collection<Runnable> tasks){
		ExecutorService es = THREAD_POOL.getThreadPoolExecutor();
		for(Runnable task : tasks){
			es.submit(task);
		}
	}

	/**
	 * 异步执行
	 * @param task
	 * @return
	 */
	public static Future asyncRun(Runnable task){
		ExecutorService es = THREAD_POOL.getThreadPoolExecutor();
		Future result = es.submit(task);
		return result;
	}

	/**
	 * 同步执行
	 * @param tasks
	 * @return
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	public static Collection syncCall(Collection<? extends Callable> tasks) throws InterruptedException, ExecutionException{
		CompletionService es = new ExecutorCompletionService(THREAD_POOL.getThreadPoolExecutor());
		List results = new ArrayList();
		for(Callable task : tasks){
			es.submit(task);
		}
		//等待结果
		for(int i=0; i<tasks.size(); i++){
			results.add(es.take().get());
		}
		return results;
	}
}
