package com.kangde.collection.service;

import java.util.List;
import java.util.Map;

import com.kangde.collection.dto.CaseCarDto;
import com.kangde.collection.dto.CaseExcelDto;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;

/**
 * 案件service
 * @author lisuo
 *
 */
public interface CaseService extends BaseService<CaseModel,String>{
	
	/** 案件分配方式:按照数量 */
	public static final String DIVISION_WAY_NUM = "1";
	/** 案件分配方式:按照金额 */
	public static final String DIVISION_WAY_MONEY = "2";
	/** 案件分配方式:综合分配 */
	public static final String DIVISION_WAY_COMPOSITE = "3";

	/**
	 * 导入Excel数据
	 * @param caseExcels Excel案件数据集合
	 * @param caseBatch 批次
	 * @param type 导入类型
	 */
	CaseBatchModel importExcelData(List<CaseExcelDto> caseExcels,CaseBatchModel caseBatch, String type);
	
	/**
	 * 分配案件
	 * @param userId 被分配人ID
	 * @param caseIds 案件ID集 
	 * @param type 分案类型
	 */
	void divisionCase(String userId, List<String> caseIds, String type);
	
	/**
	 * 计算自动分案结果
	 * @param list 需要分案的案件集合
	 * @param divisionWay 分配方式 1:按照数量,2:按照金额,3:综合分配
	 * @param userList userList 被分案人员集合（id:分案人员ID,rate:比例）
	 * @return
	 */
	List<Map<String, Object>> autoDivisionCompute(List<CaseModel> list, String divisionWay,String userList);

	/**
	 * 自动分案
	 * @param listUserGrop JSON数据,包含[{userId,caseIds}]
	 */
	void autoDivision(String listUserGrop);
	/**
	 * 更新Excel数据
	 * @param caseExcels Excel案件数据集合
	 * @param caseBatch 批次
	 * @param type 导入类型
	 */
	void updateExcelData(List<CaseExcelDto> caseExcels, String caseBatchId, String type);

	/**
	 * 修改案件状态
	 * @param state
	 * @param caseIds
	 * @param 备注说明
	 */
	void changeState(Integer state, List<String> caseIds,String remark);
	
	/**
	 * 统计信息
	 * @param condition
	 * @return
	 */
	Map<String, Object> queryStatistics(ParamCondition condition);
	
	/**
	 * 导出前查询字段
	 * @param model
	 * @return
	 */
	public List<CaseModel> findCaseByBatchId(String batchId);
	
	/**
	 * 查询批次下的所有案件
	 * @param model
	 * @return
	 */
	List<CaseModel> findBatchCodes(List<String> ids,String[] array);
	
	/**
	 * 查询批次下的所有案件 关联车贷
	 * @param model
	 * @return
	 */
	List<CaseCarDto> findBatchCar(List<String> ids,String[] array);

	/**
	 * 所查自动分案,统计案件（总数量,总总金额，已分配，为分配）
	 * @param condition
	 * @return
	 */
	Map<String, Object> queryStatisticsForCaseCounts(ParamCondition condition);

	void updateColor(String[] ids, String color);
	
	String decompose(String content);

	List<CaseModel> findCasesByIds(String[] caseIds);
	
	/**查询即将退案的案件数量*/
	int queryReminderCaseCount(int day);

	List<CaseModel> queryCaseTotoFollow();

	int queryDivisionCaseCount();
}
