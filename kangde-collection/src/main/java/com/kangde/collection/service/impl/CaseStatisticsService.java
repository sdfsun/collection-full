package com.kangde.collection.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CaseMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 
 * @Description: 案件统计
 * @author lidengwen
 * @date 2016年8月19日 下午2:38:04
 *
 */
@Service()
public class CaseStatisticsService {

	@Autowired
	private CaseMapper caseMapper;

	public Map<String, Object> queryStatistics(String querysql) {
		StringBuilder sql = new StringBuilder("select ")
				// 案件总数
				.append("count(tb.id) AS total_case_count,")
				// 案件总金额
				.append("ifnull(sum(tb.case_money), 0) as total_case_money,")
				// 已还款案件量
				.append("count(if(tb.paid_num>0,tb.paid_num, null)) as already_case_count,")
				// 已还款金额
				.append("ifnull(sum(tb.paid_num), 0) as total_already_money,")
				// CP
				.append("ifnull(sum(tb.cp_money), 0) as total_cp_money,")
				// PTP
				.append("ifnull(sum(tb.ptp_money), 0) as total_ptp_money, ")
				
				
				.append("ifnull(sum(tb.agent_rate * tb.paid_num), 0) as total_brokerage ")
				// 嵌套之前的查询结果
				.append("from (").append(querysql).append(") as tb ");
		ParamCondition condition = new ParamCondition();
		condition.put("dynamicSql", sql.toString());
		return caseMapper.statistics(condition);
	}

}
