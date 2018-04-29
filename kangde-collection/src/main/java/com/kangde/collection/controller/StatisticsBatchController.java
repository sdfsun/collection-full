package com.kangde.collection.controller;


import java.util.ArrayList;
import java.util.List;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kangde.collection.dto.StatisticsBatchDto;
import com.kangde.collection.service.StatisticsService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/statisticsBatch")
public class StatisticsBatchController extends RestfulUrlController<StatisticsBatchDto, String> {

	@Autowired
	private StatisticsService statisticsService;

	/**
	 * 批次统计展示方法
	 */
	@RequestMapping(value = "queryBatch")
	@ResponseBody
	public SearchResult<StatisticsBatchDto> queryBatch() {
		ParamCondition condition = parseCondition("*");
		SearchResult<StatisticsBatchDto> queryBatch = statisticsService.queryBatch(condition);
		List<StatisticsBatchDto> list = queryBatch.getRows();
		List<StatisticsBatchDto> footer = new ArrayList<StatisticsBatchDto>();
		StatisticsBatchDto dto = new StatisticsBatchDto();
		for (StatisticsBatchDto batchDto : list) {
				dto.setCase_count(batchDto.getCase_count()+dto.getCase_count());
				dto.setTotal_case_money(batchDto.getTotal_case_money()+dto.getTotal_case_money());
				dto.setPaid_num(batchDto.getPaid_num()+dto.getPaid_num());
				dto.setPaid_case_count(batchDto.getPaid_case_count()+dto.getPaid_case_count());
				dto.setPaid_achieve(batchDto.getPaid_achieve()+ dto.getPaid_achieve());
				dto.setBack_paid(batchDto.getBack_paid()+dto.getBack_paid());
				
				dto.setCp_money(batchDto.getCp_money()+dto.getCp_money());
				dto.setCp_back_paid(batchDto.getCp_back_paid()+dto.getCp_back_paid());
				dto.setCp_achieve(batchDto.getCp_achieve()+dto.getCp_achieve());
				dto.setCp_case_count(batchDto.getCp_case_count()+dto.getCp_case_count());
				
		}
		dto.setHun_average_out("—");
		dto.setPart_average_out("—");
		dto.setAverage_rate("—");
		dto.setBatch_code("合计");
		footer.add(dto);
		queryBatch.setFooter(footer);
		return queryBatch;
	}

	/** 导出所选委托方统计 */
	@RequestMapping(value = "/exportSelected")
	public ModelAndView exportSelected(String[] ids){
		List<StatisticsBatchDto> list=statisticsService.queryExport(Arrays.asList(ids));
		return super.createExcelView("statisticsBatch", list, "所选批次统计", null, null);
	}

	/** 导出所查委托方统计 */
	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(){
		ParamCondition condition = parseCondition("*");
		List<StatisticsBatchDto> list=statisticsService.exportQueryExcel(condition);
		StatisticsBatchDto dto = new StatisticsBatchDto();
		for (StatisticsBatchDto batchDto : list) {
				dto.setAchieve(batchDto.getAchieve()+dto.getAchieve());
				dto.setCase_count(batchDto.getCase_count()+dto.getCase_count());
				dto.setCp_money(batchDto.getCp_money()+ dto.getCp_money());
				dto.setFamily_case_count(batchDto.getFamily_case_count()+dto.getFamily_case_count());
				dto.setInvolve_case_count(batchDto.getInvolve_case_count()+dto.getInvolve_case_count());
				dto.setLose_case_count(batchDto.getLose_case_count()+dto.getLose_case_count());
				dto.setNegotiation_case_count(batchDto.getNegotiation_case_count()+dto.getNegotiation_case_count());
				dto.setNew_case_count(batchDto.getNew_case_count()+dto.getNew_case_count());
				dto.setOneself_case_count(batchDto.getOneself_case_count()+dto.getOneself_case_count());
				dto.setPaid_num(batchDto.getPaid_num()+dto.getPaid_num());
				dto.setPtp_money(batchDto.getPtp_money()+dto.getPtp_money());
				dto.setSomeone_case_count(batchDto.getSomeone_case_count()+dto.getSomeone_case_count());
				dto.setTarget(batchDto.getTarget()+dto.getTarget());
				dto.setTotal_case_money(batchDto.getTotal_case_money()+dto.getTotal_case_money());
				dto.setBatch_code("合计");
		}
		list.add(dto);
		return super.createExcelView("statisticsBatch", list, "所查批次统计", null, null);
	}
	

	@Override
	protected String getBaseName() {
		return "collection/statisticsBatch/";
	}

}
