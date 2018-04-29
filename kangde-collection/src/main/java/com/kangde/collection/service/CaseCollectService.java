package com.kangde.collection.service;

import java.util.List;
import java.util.Map;

import com.kangde.collection.dto.CaseCollectViewDto;
import com.kangde.commons.service.BaseService;
import com.kangde.commons.vo.ParamCondition;

/**
 * 			
  * @Description: 案件风控
  * @author lidengwen
  * @date 2016年6月23日 下午1:40:21
  *
 */
public interface CaseCollectService extends BaseService<CaseCollectViewDto,String>{
	/**
	 * 修改案件状态
	 * @param state
	 * @param caseIds
	 */
	void changeState(Integer state, List<String> caseIds);
	/**
	 * 统计信息
	 * @param condition
	 * @return
	 */
	Map<String, Object> queryStatistics(ParamCondition condition);
}
