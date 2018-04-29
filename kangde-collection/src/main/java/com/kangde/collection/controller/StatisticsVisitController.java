package com.kangde.collection.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.StatisticsApplyDto;
import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsVisitDto;
import com.kangde.collection.service.StatisticsVisitService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/statisticsVisit")
public class StatisticsVisitController extends RestfulUrlController<StatisticsVisitDto, String> {

	@Autowired
	private StatisticsVisitService statisticsService;
	/**
	 * 委托方统计展示方法
	 */
	@RequestMapping(value = "queryVisit")
	@ResponseBody
	public SearchResult<StatisticsVisitDto> queryVisit() {
		ParamCondition condition = parseCondition("*");
		SearchResult<StatisticsVisitDto> queryVisit = statisticsService.queryVisit(condition);

		List<StatisticsVisitDto> list = queryVisit.getRows();
		List<StatisticsVisitDto> footer = new ArrayList<StatisticsVisitDto>();
		StatisticsVisitDto dto = new StatisticsVisitDto();
		
		dto.setActual_visit_time("合计");
		for (StatisticsVisitDto visitDto : list) {

			DecimalFormat df = new DecimalFormat("######0.00");  
			dto.setAverage_case_visit_count(Double.parseDouble(df.format(visitDto.getAverage_case_visit_count()+dto.getAverage_case_visit_count())));
			dto.setAverage_visit_count(Double.parseDouble(df.format(visitDto.getAverage_visit_count()+dto.getAverage_visit_count())));
			dto.setFamily_visit_count(visitDto.getFamily_visit_count()+dto.getFamily_visit_count());
			dto.setNoaddress_visit_count(visitDto.getNoaddress_visit_count()+dto.getNoaddress_visit_count());
			dto.setNoone_visit_count(visitDto.getNoone_visit_count()+dto.getNoone_visit_count());
			dto.setOneself_visit_count(visitDto.getOneself_visit_count()+dto.getOneself_visit_count());
			dto.setRemove_visit_count(visitDto.getRemove_visit_count()+dto.getRemove_visit_count());
			dto.setVisit_count(visitDto.getVisit_count()+dto.getVisit_count());
			dto.setVisit_people_count(visitDto.getVisit_people_count()+dto.getVisit_people_count());
			dto.setCase_count(visitDto.getCase_count()+dto.getCase_count());
			
		}

		footer.add(dto);
		queryVisit.setFooter(footer);
		return queryVisit;
	}

	/** 导出所选外放统计 */
	@RequestMapping(value = "/exportSelected")
	public ModelAndView exportSelected(String[] ids){
		List<StatisticsVisitDto> list=statisticsService.queryExport(Arrays.asList(ids));
		return super.createExcelView("statisticsVisit", list, "所选外访统计", null, null);
	}
	
	/** 导出所查外放统计 */
	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(){
		ParamCondition condition = parseCondition("*");
		List<StatisticsVisitDto> list=statisticsService.exportQueryExcel(condition);
		StatisticsVisitDto dto = new StatisticsVisitDto();
		
		dto.setActual_visit_time("合计");
		for (StatisticsVisitDto visitDto : list) {

			dto.setAverage_case_visit_count(visitDto.getAverage_case_visit_count()+dto.getAverage_case_visit_count());
			dto.setAverage_visit_count(visitDto.getAverage_visit_count()+dto.getAverage_visit_count());
			dto.setFamily_visit_count(visitDto.getFamily_visit_count()+dto.getFamily_visit_count());
			dto.setNoaddress_visit_count(visitDto.getNoaddress_visit_count()+dto.getNoaddress_visit_count());
			dto.setNoone_visit_count(visitDto.getNoone_visit_count()+dto.getNoone_visit_count());
			dto.setOneself_visit_count(visitDto.getOneself_visit_count()+dto.getOneself_visit_count());
			dto.setRemove_visit_count(visitDto.getRemove_visit_count()+dto.getRemove_visit_count());
			dto.setVisit_count(visitDto.getVisit_count()+dto.getVisit_count());
			dto.setVisit_people_count(visitDto.getVisit_people_count()+dto.getVisit_people_count());
			
		}

		list.add(dto);
		return super.createExcelView("statisticsVisit", list, "所查外访统计", null, null);
	}

	@Override
	protected String getBaseName() {
		return "collection/statisticsVisit/";
	}

}
