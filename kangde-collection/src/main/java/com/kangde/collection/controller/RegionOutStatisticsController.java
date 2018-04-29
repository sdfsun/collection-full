package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kangde.collection.model.RegionOutStatistics;
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
@RequestMapping("collection/regionOutStatistics")
public class RegionOutStatisticsController extends RestfulUrlController<RegionOutStatistics,String> {
//	@Autowired
//	private RegionOutStatisticsService regionOutStatisticsService;
	
	@Override
	public SearchResult<RegionOutStatistics> queryForPage() {
		ParamCondition condition = parseCondition("*");
		
		String emId=condition.get("emId");
		if (StringUtils.isNotBlank(emId)) {
			String[] emIds = emId.split(",");
			condition.put("emIds", emIds);
		}
		
		SearchResult<RegionOutStatistics> queryForPage = super.queryForPage(condition);
		List<RegionOutStatistics> list = queryForPage.getRows();
		List<RegionOutStatistics> footer = new ArrayList<RegionOutStatistics>();
		RegionOutStatistics dto = new RegionOutStatistics();
		for (RegionOutStatistics callRecord : list) {
			
			dto.setCallOutCount(callRecord.getCallOutCount()+dto.getCallOutCount());
			dto.setCallOutConnectCount(callRecord.getCallOutConnectCount()+dto.getCallOutConnectCount());
			dto.setCallOutConnectDuration(DateUtil.timeAdd(callRecord.getCallOutConnectDuration(),dto.getCallOutConnectDuration()));
		}
		dto.setBridgeDurationDayAvg("—");
		dto.setCallOutConnectDurationAvg("—");
		dto.setCallOutConnectRate("—");
		dto.setEmpName("合计");
		dto.setCno("—");
		dto.setCustomerProvince("—");
		dto.setCustomerCity("—");
		footer.add(dto);
		queryForPage.setFooter(footer);
		return queryForPage;
	}
	@Override
	protected String getBaseName() {
		return "collection/regionOutStatistics/";
	}
}
