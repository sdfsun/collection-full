package com.kangde.collection.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.StatisticsApplyDto;
import com.kangde.collection.dto.StatisticsAssistDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.service.StatisticsAssistService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/statisticsAssist")
public class StatisticsAssistController extends RestfulUrlController<StatisticsAssistDto, String> {

	@Autowired
	private StatisticsAssistService statisticsService;
	/**
	 * 统计展示方法 Assist
	 */
	@RequestMapping(value = "queryAssist")
	@ResponseBody
	public SearchResult<StatisticsAssistDto> queryAssist() {
		ParamCondition condition = parseCondition("*");
		SearchResult<StatisticsAssistDto> queryAssist = statisticsService.queryAssist(condition);
		
		List<StatisticsAssistDto> list = queryAssist.getRows();
		List<StatisticsAssistDto> footer = new ArrayList<StatisticsAssistDto>();
		StatisticsAssistDto dto = new StatisticsAssistDto();
		
		dto.setSur_time("合计");
		for (StatisticsAssistDto assistDto : list) {

			dto.setApply_count(assistDto.getApply_count()+dto.getApply_count());
			dto.setAverage_case_apply_count(assistDto.getAverage_case_apply_count()+dto.getAverage_case_apply_count());
			dto.setDx(assistDto.getDx()+dto.getDx());
			dto.setHj(assistDto.getHj()+dto.getHj());
			dto.setShb(assistDto.getShb()+dto.getShb());
			
		}

		footer.add(dto);
		queryAssist.setFooter(footer);
		return queryAssist;
	}

	@RequestMapping(value = "/exportSelected")
	public ModelAndView exportSelected(String[] ids){
		List<StatisticsAssistDto> list=statisticsService.queryExport(Arrays.asList(ids));
		return super.createExcelView("statisticsAssist", list, "所选查资统计", null, null);
	}

	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(){
		ParamCondition condition = parseCondition("*");
		List<StatisticsAssistDto> list=statisticsService.exportQueryExcel(condition);
		StatisticsAssistDto dto = new StatisticsAssistDto();
		
		dto.setSur_time("合计");
		for (StatisticsAssistDto assistDto : list) {

			dto.setApply_count(assistDto.getApply_count()+dto.getApply_count());
			dto.setAverage_case_apply_count(assistDto.getAverage_case_apply_count()+dto.getAverage_case_apply_count());
			dto.setDx(assistDto.getDx()+dto.getDx());
			dto.setHj(assistDto.getHj()+dto.getHj());
			dto.setShb(assistDto.getShb()+dto.getShb());
			
		}

		list.add(dto);
		return super.createExcelView("statisticsAssist", list, "所查查资统计", null, null);
	}
	
	@Override
	protected String getBaseName() {
		return "collection/statisticsAssist/";
	}

}
