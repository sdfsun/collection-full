package com.kangde.collection.timer.handler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kangde.collection.mapper.CaseBatchMapper;
import com.kangde.collection.mapper.CaseMapper;
import com.kangde.collection.model.CaseBatchModel;
import com.kangde.collection.model.CaseModel;
import com.kangde.commons.CoreConst;

/**
 * 自动退案处理器,用于控制事物
 * @author lisuo
 *
 */
@Component
public class AutoBackCaseHandler {
	@Autowired
	private CaseMapper caseMapper;
	@Autowired
	private CaseBatchMapper caseBatchMapper;

	//自动退案
	public void autoBackCase(){
		Date now = new Date();
		//查询委案截止日期比今天小的批次号
		List<String> batchIds = caseBatchMapper.findIdWithEnddate(now);
		if(CollectionUtils.isNotEmpty(batchIds)){
			//查询批次下对应的案件
			for(String batchId:batchIds){
				//修改批次状态为2已导入已退案
				changeCaseBatchState(batchId);
			}
		}
		
		List<CaseModel> cases = caseMapper.findIdWithEnddate(now);
		if(CollectionUtils.isNotEmpty(cases)){
			//修改案件状态为退案
			changeCaseState(cases);
		}
		
	}
		
	//修改案件状态为退案
	private void changeCaseState(List<CaseModel> cases){
		if(CollectionUtils.isNotEmpty(cases)){
			Map<String, Object> params = new HashMap<>();
			params.put("modifyTime", new Date());
			params.put("modifyEmpId", CoreConst.SYSTEM_TIMER_USER);
			params.put("state", CaseModel.STATE_BACK);
			List<String> caseIds = new ArrayList<>(cases.size());
			for(CaseModel c:cases){
				caseIds.add(c.getId());
			}
			params.put("caseIds",caseIds);
			caseMapper.changeState(params);
		}
	}
	
	//修改批次状态为2已导入已退案
	private void changeCaseBatchState(String caseBatchId){
		CaseBatchModel caseBatch = new CaseBatchModel();
		caseBatch.setId(caseBatchId);
		caseBatch.setWdcDesc("委案截止日期已到,系统定时自动更新");
		caseBatch.setModifyTime(new Date());
		caseBatch.setModifyEmpId(CoreConst.SYSTEM_TIMER_USER);
		caseBatch.setState(2);
		caseBatchMapper.updateForTimer(caseBatch);
	}

}
