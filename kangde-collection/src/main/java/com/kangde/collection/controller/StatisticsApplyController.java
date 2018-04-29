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
import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.collection.service.StatisticsApplyService;
import com.kangde.commons.util.DateUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/statisticsApply")
public class StatisticsApplyController extends RestfulUrlController<StatisticsApplyDto, String> {

	@Autowired
	private StatisticsApplyService statisticsService;

	/**
	 * 统计展示方法 Apply
	 */
	@RequestMapping(value = "queryApply")
	@ResponseBody
	public SearchResult<StatisticsApplyDto> queryApply() {
		ParamCondition condition = parseCondition("*");
		SearchResult<StatisticsApplyDto> queryApply = statisticsService.queryApply(condition);

		List<StatisticsApplyDto> list = queryApply.getRows();
		List<StatisticsApplyDto> footer = new ArrayList<StatisticsApplyDto>();
		StatisticsApplyDto dto = new StatisticsApplyDto();
		
		dto.setSur_time("合计");
		for (StatisticsApplyDto applyDto : list) {

			dto.setApply_count(applyDto.getApply_count()+dto.getApply_count());
			dto.setAverage_case_apply_count(applyDto.getAverage_case_apply_count()+dto.getAverage_case_apply_count());
			dto.setCharge_count(applyDto.getCharge_count()+dto.getCharge_count());
			dto.setCourt_count(applyDto.getCourt_count()+dto.getCourt_count());
			dto.setCustomer_count(applyDto.getCustomer_count()+dto.getCustomer_count());
			dto.setPolice_count(applyDto.getPolice_count()+dto.getPolice_count());
			
		}

		footer.add(dto);
		queryApply.setFooter(footer);
		
		return queryApply;
	}

	/** 导出所选协催统计 */
	@RequestMapping(value = "/exportSelected")
	public ModelAndView exportSelected(String[] ids) {
		List<StatisticsApplyDto> list = statisticsService.queryExport(Arrays.asList(ids));
		return super.createExcelView("statisticsApply", list, "所选协催统计", null, null);
	}

	/** 导出所查委托方统计 */
	@RequestMapping(value = "/exportQueryExcel", method = RequestMethod.POST)
	public ModelAndView exportExcel() {
		ParamCondition condition = parseCondition("*");
		List<StatisticsApplyDto> list = statisticsService.exportQueryExcel(condition);
		StatisticsApplyDto dto = new StatisticsApplyDto();
		dto.setSur_time("合计");
		for (StatisticsApplyDto applyDto : list) {
			dto.setApply_count(applyDto.getApply_count()+dto.getApply_count());
			dto.setAverage_case_apply_count(applyDto.getAverage_case_apply_count()+dto.getAverage_case_apply_count());
			dto.setCharge_count(applyDto.getCharge_count()+dto.getCharge_count());
			dto.setCourt_count(applyDto.getCourt_count()+dto.getCourt_count());
			dto.setCustomer_count(applyDto.getCustomer_count()+dto.getCustomer_count());
			dto.setPolice_count(applyDto.getPolice_count()+dto.getPolice_count());
			
		}
		list.add(dto);
		return super.createExcelView("statisticsApply", list, "所查催记统计", null, null);
	}

	@Override
	protected String getBaseName() {
		return "collection/statisticsApply/";
	}

}
