package com.kangde.collection.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kangde.collection.model.CnoInStatistics;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

/**
 * 电催统计 Controller
 * @author zhangyj
 * @date 2016年12月29日
 */
@Controller
@RequestMapping("collection/cnoInStatistics")
public class CnoInStatisticsController extends RestfulUrlController<CnoInStatistics,String> {
//	@Autowired
//	private CnoInStatisticsService cnoInStatisticsService;

	@Override
	public SearchResult<CnoInStatistics> queryForPage() {
		ParamCondition condition = parseCondition("*");
		
		String emId=condition.get("emId");
		if (StringUtils.isNotBlank(emId)) {
			String[] emIds = emId.split(",");
			condition.put("emIds", emIds);
		}
		
		SearchResult<CnoInStatistics> queryForPage = super.queryForPage(condition);
		List<CnoInStatistics> list = queryForPage.getRows();
		List<CnoInStatistics> footer = new ArrayList<CnoInStatistics>();
		CnoInStatistics dto = new CnoInStatistics();
		for (CnoInStatistics callRecord : list) {
			
			dto.setCallInCount(callRecord.getCallInCount()+dto.getCallInCount());
			dto.setCallInConnectCount(callRecord.getCallInConnectCount()+dto.getCallInConnectCount());
			dto.setCallInConnectDuration(DateUtil.timeAdd(callRecord.getCallInConnectDuration(),dto.getCallInConnectDuration()));
		}
		dto.setBridgeDurationDayAvg("—");
		dto.setCallInConnectDurationAvg("—");
		dto.setCno("—");
		dto.setCallInConnectRate("—");
		dto.setEmpName("合计");
		footer.add(dto);
		queryForPage.setFooter(footer);
		return queryForPage;
	}
	@Override
	protected String getBaseName() {
		return "collection/cnoInStatistics/";
	}
	
}
