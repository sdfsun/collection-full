package com.kangde.collection.controller;

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

import com.kangde.collection.dto.CaseCollectViewDto;
import com.kangde.collection.dto.ControllerMessageDto;
import com.kangde.collection.dto.HelpMeDto;
import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.service.StatisticsEntrustService;
import com.kangde.collection.service.StatisticsService;
import com.kangde.collection.view.VisitDocView;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.VisitTemplateModel;

@Controller
@RequestMapping("collection/statisticsEntrustCaseState")
public class StatisticsEntrustCaseStateController extends RestfulUrlController<StatisticsEntrustDto, String> {

	@Autowired
	private StatisticsEntrustService statisticsService;
	/**
	 * 委托方统计展示方法
	 */
	@RequestMapping(value = "queryEntrustCaseState")
	@ResponseBody
	public SearchResult<StatisticsEntrustDto> queryEntrust() {
		ParamCondition condition = parseCondition("*");
		
		String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			String[] entrustIds = entrustId.split(",");
			condition.put("entrustIds", entrustIds);
		}
		
		SearchResult<StatisticsEntrustDto> queryEntrust = statisticsService.queryEntrustCaseState(condition);
		List<StatisticsEntrustDto> list = queryEntrust.getRows();
		List<StatisticsEntrustDto> footer = new ArrayList<StatisticsEntrustDto>();
		StatisticsEntrustDto dto = new StatisticsEntrustDto();
		
		for (StatisticsEntrustDto entrustDto : list) {
			dto.setCase_count(entrustDto.getCase_count()+dto.getCase_count());
			dto.setFamily_case_count(entrustDto.getFamily_case_count()+dto.getFamily_case_count());
			dto.setInvolve_case_count(entrustDto.getInvolve_case_count()+dto.getInvolve_case_count());
			dto.setLose_case_count(entrustDto.getLose_case_count()+dto.getLose_case_count());
			dto.setNegotiation_case_count(entrustDto.getNegotiation_case_count()+dto.getNegotiation_case_count());
			dto.setNew_case_count(entrustDto.getNew_case_count()+dto.getNew_case_count());
			dto.setOneself_case_count(entrustDto.getOneself_case_count()+dto.getOneself_case_count());
			dto.setSomeone_case_count(entrustDto.getSomeone_case_count()+dto.getSomeone_case_count());
			dto.setTotal_case_money(entrustDto.getTotal_case_money()+dto.getTotal_case_money());
			dto.setStop_case_count(entrustDto.getStop_case_count()+dto.getStop_case_count());
			dto.setBack_case_count(entrustDto.getBack_case_count()+dto.getBack_case_count());
			dto.setPromise_case_count(entrustDto.getPromise_case_count()+dto.getPromise_case_count());
			dto.setCheat_case_count(entrustDto.getCheat_case_count()+dto.getCheat_case_count());
	}
		dto.setName("合计");
	footer.add(dto);
	queryEntrust.setFooter(footer);
		
		return queryEntrust;
	}
	
	/** 导出所选委托方统计 */
	@RequestMapping(value = "/exportSelected")
	public ModelAndView exportSelected(String[] ids){
		List<StatisticsEntrustDto> list=statisticsService.queryExport(Arrays.asList(ids));
		return super.createExcelView("statisticsEntrust", list, "所选委托方统计", null, null);
	}
	

	/** 导出所查委托方统计 */
	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(){
		ParamCondition condition = parseCondition("*");
		List<StatisticsEntrustDto> list=statisticsService.exportQueryExcel(condition);
		StatisticsEntrustDto dto = new StatisticsEntrustDto();
		for (StatisticsEntrustDto entrustDto : list) {
			dto.setAchieve(entrustDto.getAchieve()+dto.getAchieve());
			dto.setCase_count(entrustDto.getCase_count()+dto.getCase_count());
			dto.setCp_money(entrustDto.getCp_money()+ dto.getCp_money());
			dto.setFamily_case_count(entrustDto.getFamily_case_count()+dto.getFamily_case_count());
			dto.setInvolve_case_count(entrustDto.getInvolve_case_count()+dto.getInvolve_case_count());
			dto.setLose_case_count(entrustDto.getLose_case_count()+dto.getLose_case_count());
			dto.setNegotiation_case_count(entrustDto.getNegotiation_case_count()+dto.getNegotiation_case_count());
			dto.setNew_case_count(entrustDto.getNew_case_count()+dto.getNew_case_count());
			dto.setOneself_case_count(entrustDto.getOneself_case_count()+dto.getOneself_case_count());
			dto.setPaid_num(entrustDto.getPaid_num()+dto.getPaid_num());
			dto.setPtp_money(entrustDto.getPtp_money()+dto.getPtp_money());
			dto.setSomeone_case_count(entrustDto.getSomeone_case_count()+dto.getSomeone_case_count());
			dto.setTarget(entrustDto.getTarget()+dto.getTarget());
			
			dto.setTotal_case_money(entrustDto.getTotal_case_money()+dto.getTotal_case_money());
			dto.setStop_case_count(entrustDto.getStop_case_count()+dto.getStop_case_count());
			dto.setBack_case_count(entrustDto.getBack_case_count()+dto.getBack_case_count());
			dto.setPromise_case_count(entrustDto.getPromise_case_count()+dto.getPromise_case_count());
			dto.setCheat_case_count(entrustDto.getCheat_case_count()+dto.getCheat_case_count());
	}
		dto.setName("合计");
		list.add(dto);
		return super.createExcelView("statisticsEntrust", list, "所查委托方统计", null, null);
	}
	
	@Override
	protected String getBaseName() {
		return "collection/statisticsEntrustCaseState/";
	}

}
