package com.kangde.collection.util;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.kangde.commons.util.DateUtil;

/**
 * 流水号生成工具类
 * @author lisuo
 *
 */
public abstract class SnUtil {
	
	/** Redis 批次自增Key */
	public static final String REDIS_CASE_BATCH_KEY = "com.kangde.collection.util.SnUtil.REDIS_CASE_BATCH_KEY_INCR_NO";
	/** 批次号 位数长度 */
	private static final Integer DEFAULT_CASE_BATCH_LEN = 3;
	/** 案件序列号 位数长度 */
	private static final Integer DEFAULT_CASE_LEN = 5;
	/** Zero */
	private static final String ZERO = "0";
	
	private static Long getCaseBatchIncr(){
		Long incr = RedisUtil.incr(REDIS_CASE_BATCH_KEY);
		//如果数据是第一次加载,由Redis生成的key
		if(incr==1){
			//设置过期时间
			Date nextDay = DateUtil.getDayMin(new Date(), 1);
			long startSeconds = TimeUnit.MILLISECONDS.toSeconds(nextDay.getTime());
			long endSeconds = TimeUnit.MILLISECONDS.toSeconds(new Date().getTime());
			Long expireSeconds = startSeconds-endSeconds;
			RedisUtil.expire(REDIS_CASE_BATCH_KEY, expireSeconds.intValue());
		}
		return incr;
	}
	
	/**
	 * 生成案件序列号
	 * @param batchCode 案件批次号
	 * @param index 案件生成的索引位置（后缀）
	 * @return
	 */
	public static String getCaseSn(String batchCode,int index){
		StringBuilder no = new StringBuilder(batchCode);
		int indexLen = String.valueOf(index).length();
		while(indexLen < DEFAULT_CASE_LEN){
			no.append(ZERO);
			indexLen++;
		}
		no.append(index);
		return no.toString();
	}
	
	/**
	 * 生成案件批次号
	 * @param entrustCode 委托方简码
	 * @param caseTypeCode 案件类型简码
	 * @return
	 */
	public static String getCaseBatchSn(String entrustCode,String caseTypeCode){
		StringBuilder no = new StringBuilder(entrustCode).append(caseTypeCode);
		//生成日期
		no.append(DateUtil.date2Str(new Date(), DateUtil.DATE_FORMAT));
		//从Redis获取今天批次自增的数量
		Long index = getCaseBatchIncr();
		int indexLen = String.valueOf(index).length();
		while(indexLen < DEFAULT_CASE_BATCH_LEN){
			no.append(ZERO);
			indexLen++;
		}
		no.append(index);
		return no.toString();
	}
	/**
	 * 生成全简码
	 * @param entrustCode 委托方简码
	 * @param caseTypeCode 案件类型简码
	 * @return
	 */
	public static String getWholeSn(String entrustCode,String caseTypeCode,String handle,String caseSourceCode){
		StringBuilder no = new StringBuilder(entrustCode)
								.append(caseTypeCode)
								.append(handle)
								.append(caseSourceCode);
		
		return no.toString();
	}
	
}
