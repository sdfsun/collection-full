package com.kangde.collection.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.dto.CaseCarDto;
import com.kangde.collection.dto.CaseCollectViewDto;
import com.kangde.collection.model.CaseModel;
import com.kangde.collection.vo.CaseJointDebtVo;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 案件mapper
 * 
 * @author lisuo
 *
 */
public interface CaseMapper extends BaseMapper<CaseModel, String> {
	
	public List<CaseJointDebtVo> queryCaseJointDebts(ParamCondition condition);
	public List<CaseCollectViewDto> queryCaseCollect(ParamCondition condition);
	
	/**
	 * 更新案件分配人信息
	 * @param params
	 */
	public int updateCaseAssignedInfo(Map<String, Object> params);
	
	/**
	 * 修改案件状态
	 * @param params
	 */
	public void changeState(Map<String, Object> params);
	
	/**
	 * 统计
	 * @param condition
	 * @return
	 */
	public Map<String, Object> statistics(ParamCondition condition);
	/**
	 * （逻辑删除）
	 * @param 
	 * @return
	 */
	int softDelete(List<String> caseIds);
	/**
	 * 恢复批次时 同时恢复批次下的案件
	 * @param batchId
	 * @return
	 */
	int updateStatusNomal(String batchId);
	/**
	 * 撤回批次时， 撤回批次下的案件
	 * @param batchId
	 * @return
	 */
	int updateStatusBack(String batchId);
	/**
	 * 修改最后跟进日期
	 * @param id
	 * @return
	 */
	int updateLastPhoneForId(@Param(value = "id") String id,@Param(value = "lastPhoneTime") Date lastPhoneTime);
	/**
	 * 导出前查询字段
	 * @param batchId
	 * @return
	 */
	List<CaseModel> findCaseByBatchId(String batchId);
	
	/**
	 * 查询部分信息,通过批次ID
	 * @param batchId
	 * @return
	 */
	List<CaseModel> findPartByBatchId(String batchId);
	
	/**
	 * 查找委案截止日期小于now的
	 * @param now
	 * @return
	 */
	List<CaseModel> findIdWithEnddate(Date now);
	
	/**
	 * 查询批次下的所有案件
	 * @param model
	 * @return
	 */
	List<CaseModel> findBatchCodes(Map<String, Object> params);
	List<CaseModel> findBatchId(String batchId);
	/** 根据caseId批量查询案件信息*/
	List<CaseModel> findCasesByIds(String []caseIds);
	/** 根据外访id批量查询案件信息*/
	List<CaseModel> findcaseByVisitrecordIds(String[] visitrecordIds);
	
    void updateForIds(Map<String, Object> params);
		
    String getcaseIdByCaseFileNo(String caseFileNo);
    String getcaseIdByCaseCode(String caseCode);
    
	public int updateCaseInfo(@Param(value = "batchId") String batchId, @Param(value = "caseDate") Date caseDate, @Param(value = "endDate") Date endDate);
	
	/**
	 * 查询批次下的所有案件 关联car
	 * @param model
	 * @return
	 */
	List<CaseCarDto> findBatchCar(Map<String, Object> params);
	public int findReminderCaseCount(@Param(value = "userId")String userId,@Param(value = "backDateBegin")String backDateBegin,@Param(value = "backDateEnd") String backDateEnd);
	public List<CaseModel> caseToFollow(@Param(value = "userId")String userId,@Param(value = "now") String now);
	public int divisionCaseCount(@Param(value = "attachEntrustId")String attachEntrustId, @Param(value = "queryOrgs")String queryOrgs, @Param(value = "orgId")String orgId, @Param(value = "orgPath")String orgPath, @Param(value = "loginName")String loginName);
}