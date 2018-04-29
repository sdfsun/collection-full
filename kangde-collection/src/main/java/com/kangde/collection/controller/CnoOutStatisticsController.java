package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.kangde.collection.model.CnoOutStatistics;
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
@RequestMapping("collection/cnoOutStatistics")
public class CnoOutStatisticsController extends RestfulUrlController<CnoOutStatistics,String> {
//	@Autowired
//	private CnoOutStatisticsService cnoOutStatisticsService;
	
	@Override
	public SearchResult<CnoOutStatistics> queryForPage() {
		ParamCondition condition = parseCondition("*");
		
		SearchResult<CnoOutStatistics> queryForPage = super.queryForPage(condition);
		List<CnoOutStatistics> list = queryForPage.getRows();
		List<CnoOutStatistics> footer = new ArrayList<CnoOutStatistics>();
		CnoOutStatistics dto = new CnoOutStatistics();
		for (CnoOutStatistics callRecord : list) {
			
			dto.setCallOutCount(callRecord.getCallOutCount()+dto.getCallOutCount());
			dto.setCallOutConnectCount(callRecord.getCallOutConnectCount()+dto.getCallOutConnectCount());
			dto.setCallOutConnectDuration(DateUtil.timeAdd(callRecord.getCallOutConnectDuration(),dto.getCallOutConnectDuration()));
		}
		dto.setBridgeDurationDayAvg("—");
		dto.setCallOutConnectDurationAvg("—");
		dto.setCno("—");
		dto.setCallOutConnectRate("—");
		dto.setEmpName("合计");
		footer.add(dto);
		queryForPage.setFooter(footer);
		return queryForPage;
	}
	@Override
	protected String getBaseName() {
		return "collection/cnoOutStatistics/";
	}
}
