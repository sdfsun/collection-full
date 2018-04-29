package com.kangde.collection;

/**
 * 风控系统常量配置
 * @author lisuo
 *
 */
public abstract class CollectionConst {
	
	//文件上传路径配置
	/** 案件Excel数据上传路径 */
	public static final String CASE_EXCEL_PATH = "";
	//文件上传路径配置End
	
	
	//----------------------------------------案件操作
	//---------案件上传模板
	/** 案件上传时,P2P模板对应字典的value */
	public static final String CASE_TEMP_TYPE_P2P = "1";
	/** 案件上传时,银行模板对应字典的value */
	public static final String CASE_TEMP_TYPE_BANK = "2";
	/** 案件上传时,车贷模板对应字典的value */
	public static final String CASE_TEMP_TYPE_CAR = "3";   
	//---------案件分案状态
	/** 案件分案状态:已分配 */
	public static final String CASE_ALREADY_ASSIGN = "1";
	/** 案件分案状态:为分配 */
	public static final String CASE_NOT_ASSIGN = "0";
	//---------还款情况
	/** 0:未还款 */
	public static final String NOT_REPAY = "0";
	/** 1:部分还款 */
	public static final String PART_REPAY = "1";
	/** 2:已结清 */
	public static final String SETTLED = "2";
	//----------------------------------------案件操作End
	
}
