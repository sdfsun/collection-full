package com.kangde.collection.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.collection.mapper.CasePaidMapper;
import com.kangde.commons.vo.ParamCondition;

/**
 * 
 * @Description: 案件统计
 * @author lidengwen
 * @date 2016年8月19日 下午2:38:04
 *
 */
@Service()
public class CasePaidStatisticsService {

	@Autowired
	private CasePaidMapper caseMapper;

	public Map<String, Object> queryStatistics(String querysql) {
		StringBuilder sql = new StringBuilder("select ")
				// 已还款金额
				.append("ifnull(SUM(CASE WHEN (tb.cpState = '2') THEN IFNULL(tb.paid_num,0) ELSE 0 END),0) AS total_already_money,")
				// 佣金
				.append("ifnull(SUM(CASE WHEN tb.cpState = '1' THEN IFNULL(tb.cp_money,0) ELSE 0 END),0) AS total_cp_money,")
				// 業績
				.append("ifnull(SUM(CASE WHEN tb.cpState = '2' THEN IFNULL(tb.compBorker,0) ELSE 0 END),0) AS total_paidMoney,")
				// CP
				.append("ifnull(SUM(CASE WHEN tb.cpState = '2' THEN IFNULL(tb.commission,0) ELSE 0 END),0) AS total_achieve ")
				// 嵌套之前的查询结果
				.append("from (").append(querysql).append(") as tb ");
		ParamCondition condition = new ParamCondition();
		condition.put("dynamicSql", sql.toString());
		return caseMapper.statistics(condition);
	}

}
