package com.kangde.collection.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.kangde.collection.dto.VisitRecordDto;
import com.kangde.collection.dto.VisitRecordVo;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.model.VisitRecordModel;
import com.kangde.commons.mapper.BaseMapper;
import com.kangde.commons.vo.ParamCondition;

public interface VisitRecordMapper extends BaseMapper<VisitRecordModel, String> {

	/**
	 * 外访 待审批 待排程
	 * 
	 * @param condition
	 * @return
	 */
	List<VisitRecordDto> queryToAppointList(ParamCondition condition);

	/**
	 * 外访 待审批 待排程
	 * 
	 * @param condition
	 * @return
	 */
	List<VisitRecordDto> queryToApproveORToAppoint(ParamCondition condition);

	/**
	 * 风控管理 已排程/已完成
	 * 
	 * @param condition
	 * @return
	 */
	List<VisitRecordDto> queryAppointedORFinished(ParamCondition condition);

	List<VisitShowView> findVisitRecordIds(Map<String, Object> params);

	List<VisitShowView> queryAll(ParamCondition condition);

	List<VisitRecordVo> queryVisitRecordVo(ParamCondition condition);

	/**
	 * 导出查询勾选案件
	 * 
	 * @param model
	 * @return
	 */
	List<VisitRecordDto> selectByIds(List<String> list);
	
	List<VisitRecordModel> selectIds(List<String> list);
	

	List<VisitRecordVo> repairquery();

	void repairupdate(@Param("ids")String ids, @Param("id")String id);

	int visitCount(@Param("userId")String userId, @Param("date")String date);

	List<VisitRecordDto> caseToUploadReport(@Param("userId")String userId, @Param("yesterday")String yesterday);

	int queryToApproveORToAppointCount(@Param("area")List<String> area, @Param("state")int state);
	VisitRecordModel findByAddresId(String id);
}