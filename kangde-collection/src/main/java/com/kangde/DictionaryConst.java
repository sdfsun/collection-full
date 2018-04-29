package com.kangde;

/**
 * 字典常量配置
 * @author lisuo
 *
 */
public abstract class DictionaryConst {
	
	/** 资源类型-->字典路径 */
	public static final String RESOURCE_TYPE = "SYS_CONFIG/RESOURCE_CONFIG/RESOURCE_TYPE";
	/** 机构类型-->字典路径 */
	public static final String ORG_TYPE = "SYS_CONFIG/DEPT_CONFIG/ORG_TYPE";
	/** 批次类型-->字典路径 */
	public static final String CASE_TYPE = "DATA_MGR/CASE_BATCH_MGR/CASE_TYPE";
	/** 批次状态-->字典路径 */
	public static final String CASE_STATUS = "DATA_MGR/CASE_BATCH_MGR/CASE_STATUS";
	/** 批次委托方-->字典路径 */
	public static final String CASE_ENTRUST = "DATA_MGR/CASE_BATCH_MGR/CASE_ENTRUST";
	/** 批次风控方-->字典路径 */
	public static final String CASE_COLLECTION = "DATA_MGR/CASE_BATCH_MGR/CASE_COLLECTION";
	/** 案件审批状态-->字典路径 */
	public static final String CASE_APPROVAL = "DATA_MGR/CASE_BATCH_MGR/CASE_APPROVAL";
	/** 风控结果-->字典路径 */
	public static final String COLLECTION_RESULT = "DATA_MGR/COLLECTION_RESULT";
	/** 风控状态-->字典路径 */
	public static final String COLLECTION_STATE = "DATA_MGR/COLLECTION_STATE";
	/** 电话类型-->字典路径 */
	public static final String PHONE_TYPE = "DATA_MGR/PHONE_TYPE";
	/** 关系-->字典路径 */
	public static final String RELATION = "DATA_MGR/RELATION";
	/** 下次跟进策略-->字典路径 */
	public static final String STRATEGY = "DATA_MGR/STRATEGY";
	/** 地址类别 */
	public static final String ADDRESS_TYPE = "DATA_MGR/ADDRESS_TYPE";
	/** 性别 */
	public static final String SEX = "DATA_MGR/SEX";
	/** 系统状态-->字典路径 */
	public static final String 	CASE_STATUS_SYSTEN = "DATA_MGR/CASE_STATUS_SYSTEN";
	/** 案件操作类型-->字典路径 */
	public static final String OPER_TYPE = "DATA_MGR/OPER_TYPE";
	/** 协催类型-->字典路径 */
	public static final String HELPME = "DATA_MGR/HELPME";
	/** 协催类型-->婚姻状况 */
	public static final String GAM = "DATA_MGR/GAM";
	/** 协催类型-->文化程度 */
	public static final String WENHUA = "DATA_MGR/WENHUA";
	/** 运行商  */
	public static final String YUNXINGSHANG = "DATA_MGR/YUNXINGSHANG";
	/** 网络提供商  */
	public static final String WANGLUO = "DATA_MGR/WANGLUO";
	/** 查资状态  */
	public static final String CHAZIZHUANGTAI = "DATA_MGR/CHAZIZHUANGTAI";
	
	

	//----------------------案件导入------------------------//
	/** 案件导入模板类型-->字典路径 ,1:P2P模板,2:银行模板*/
	public static final String CASE_TEMP_TYPE = "DATA_MGR/CASE_IMP/CASE_TEMP_TYPE";
	//----------------------案件导入End------------------------//

	//----------------------案件操作------------------------//
	/** 案件状态-->字典路径 ,0:正常,1:暂停,2:关闭,3:退案,4:结清*/
	public static final String CASE_STATE = "DATA_MGR/CASE_OPT/CASE_STATE";
	/** 案件分案状态-->字典路径,0:未分配,1:已分配 */
	public static final String CASE_ASSIGN_STATE = "DATA_MGR/CASE_OPT/CASE_ASSIGN_STATE";
	/** 还款情况-->字典路径 ,0:未还款,1:部分还款,2:已结清*/
	public static final String REPAYMENT_STATUS = "DATA_MGR/CASE_OPT/REPAYMENT_STATUS";
	/** 风控状态-->字典路径 */
	public static final String CS_STATE = COLLECTION_STATE;
	/** 逾期账龄-->字典路径 */
	public static final String OVERDUEAGE = "DATA_MGR/CASE_OPT/OVERDUEAGE";
	//----------------------案件操作End------------------------//
	
	/** 地址状态-->字典路径 */
	public static final String ADDRESS_STATUS = "DATA_MGR/ADDRESS_STATUS";
	/** 案件标色 */
	public static final String CASE_COLOR = "DATA_MGR/CASE_COLOR";
	/** 外访结果 */
	public static final String VISIT_RESULT = "DATA_MGR/VISIT_RESULT";
	/** 外访原因-->字典路径 */
	public static final String VISIT_REASON = "DATA_MGR/VISIT_REASON";
	/** 外访状态-->字典路径 */
	public static final String VISIT_STATE = "DATA_MGR/CASE_OPT/VISIT_STATE";
	
	/** 外访状态-->字典路径 */
	public static final String YINGYE = "DATA_MGR/CASE_OPT/VISIT_STATE";

	//------->委托方
	public static final String CUS_TYPE = "SYS_CONFIG/ENTRUST/CUS_TYPE";
	//------->委托方:案件类型
	public static final String CASE_TYPE_ID = "SYS_CONFIG/ENTRUST/CASE_TYPE_ID";
	//------->委托方:周期
	public static final String CYCLE = "SYS_CONFIG/ENTRUST/CYCLE";
	//------->委托方:周期
	public static final String PROD_NAME = "SYS_CONFIG/ENTRUST/PROD_NAME";
	
	//------->产品管理:主体
	public static final String MAIN = "SYS_CONFIG/ENTRUST/MAIN";
	//------->产品管理:担保方式
	public static final String TEE_WAY = "SYS_CONFIG/ENTRUST/TEE_WAY";
	//------->产品管理:用途
	public static final String PURPOSE = "SYS_CONFIG/ENTRUST/PURPOSE";

	/** 还款状态-->字典路径 */
	public static final String CP_TYPE = "HG/CP_TYPE";
	
	//----------------------Quartz字典------------------------//
	/** 调度状态-->字典路径 */
	public static final String SCHEDU_LOG_STATE = "TIMER_MGR/SCHEDU_LOG_STATE";
	//----------------------Quartz字典End---------------------//

	/** 职位管理-->职位类型--->字典路径 */
	public static final String POSITION_TYPE = "SYS_CONFIG/POSITION_MGR/POSITION_TYPE";
	
	/** 员工状态-->字典路径 */
	public static final String EMP_STATUS = "EMP_INFO/EMP_STATUS";

	
	/** 信函状态-->字典路径 */
	public static final String LETTER_STATE = "LETTER_MGR/LETTER_STATE";
	/** 模板类型-->字典路径 */
	public static final String TEMPLATE_TYPE = "TEMPLATE_CONF/TEMPLATE_TYPE";
	/** 批次管理-->手次DATA_MGR/CASE_BATCH_MGR/HANDLE */
	public static final String HANDLE = "DATA_MGR/CASE_BATCH_MGR/HANDLE";
	
	/**营业管理-->客户类型DATA_MGR/CASE_BATCH_MGR/HANDLE */
	public static final String CUSTYPE = "ENTR/CUS_TYPE";
	/**营业管理-->客户类型DATA_MGR/CASE_BATCH_MGR/HANDLE */
	public static final String SER_PRO = "ENTR/SER_PRO";
	
	/**营业管理-->沟通方式 */
	public static final String YYGL_GTFS = "ENTR/YYGL_GTFS";
	
	/**批次管理-->批次状态 */
	public static final String BATCH_TYPE = "DATA_MGR/CASE_BATCH_MGR/BATCH_TYPE";
	
	
	
	
	
	
}
