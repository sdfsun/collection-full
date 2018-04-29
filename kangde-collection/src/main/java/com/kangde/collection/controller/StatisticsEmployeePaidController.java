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

import com.kangde.collection.dto.StatisticsEmployeeDto;
import com.kangde.collection.service.StatisticsEmployeeService;
import com.kangde.commons.util.NumberUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/statisticsEmployeePaid")
public class StatisticsEmployeePaidController extends RestfulUrlController<StatisticsEmployeeDto, String> {

	@Autowired
	private StatisticsEmployeeService statisticsService;

	/**
	 * 批次统计展示方法
	 */
	@RequestMapping(value = "queryEmployeePaid")
	@ResponseBody
	public SearchResult<StatisticsEmployeeDto> queryEmployee() {
		ParamCondition condition = parseCondition("*");

		String emId=condition.get("emId");
		if (StringUtils.isNotBlank(emId)) {
			String[] emIds = emId.split(",");
			condition.put("emIds", emIds);
		}
		
		SearchResult<StatisticsEmployeeDto> queryEmployee = statisticsService.queryEmployeePaid(condition);
		List<StatisticsEmployeeDto> list = queryEmployee.getRows();
		List<StatisticsEmployeeDto> footer = new ArrayList<StatisticsEmployeeDto>();
		StatisticsEmployeeDto dto = new StatisticsEmployeeDto();
		for (StatisticsEmployeeDto EmployeeDto : list) {
			dto.setCase_count(EmployeeDto.getCase_count()+dto.getCase_count());
			dto.setCp_money(EmployeeDto.getCp_money()+ dto.getCp_money());
			dto.setPaid_num(EmployeeDto.getPaid_num()+dto.getPaid_num());
			dto.setTotal_case_money(EmployeeDto.getTotal_case_money()+dto.getTotal_case_money());
			dto.setLa_count(EmployeeDto.getLa_count()+dto.getLa_count());
			dto.setLa_case_money(EmployeeDto.getLa_case_money()+dto.getLa_case_money());
			//dto.setAverage_count(NumberUtil.doubleDivide(dto.getTotal_case_money(), dto.getCase_count(), 2));
			
			dto.setCp_case_count(EmployeeDto.getCp_case_count()+dto.getCp_case_count());
			dto.setPaid_case_count(EmployeeDto.getPaid_case_count()+dto.getPaid_case_count());
			dto.setPtp_money(EmployeeDto.getPtp_money()+dto.getPtp_money());
			dto.setPtp_case_count(EmployeeDto.getPtp_case_count()+dto.getPtp_case_count());
			dto.setZlz_paid_num(EmployeeDto.getZlz_paid_num()+dto.getZlz_paid_num());
			dto.setZlz_paid_case_count(EmployeeDto.getZlz_paid_case_count()+dto.getZlz_paid_case_count());
		}
		dto.setPaid_count_rate("—");
		dto.setPaid_money_rate("—");
		dto.setPaid_rate("—");
		dto.setAverage_count("—");
		dto.setName("合计");
		footer.add(dto);
		queryEmployee.setFooter(footer);
		return queryEmployee;
	}

	/** 导出所选委托方统计 */
	@RequestMapping(value = "/exportSelected")
	public ModelAndView exportSelected(String[] ids){
		List<StatisticsEmployeeDto> list=statisticsService.queryExport(Arrays.asList(ids));
		return super.createExcelView("statisticsEmployee", list, "所选批次统计", null, null);
	}

	/** 导出所查委托方统计 */
	@RequestMapping(value="/exportQueryExcel",method=RequestMethod.POST)
	public ModelAndView exportExcel(){
		ParamCondition condition = parseCondition("*");
		List<StatisticsEmployeeDto> list=statisticsService.exportQueryExcel(condition);
		StatisticsEmployeeDto dto = new StatisticsEmployeeDto();
		for (StatisticsEmployeeDto EmployeeDto : list) {
				dto.setAchieve(EmployeeDto.getAchieve()+dto.getAchieve());
				dto.setCase_count(EmployeeDto.getCase_count()+dto.getCase_count());
				dto.setCp_money(EmployeeDto.getCp_money()+ dto.getCp_money());
				dto.setFamily_case_count(EmployeeDto.getFamily_case_count()+dto.getFamily_case_count());
				dto.setInvolve_case_count(EmployeeDto.getInvolve_case_count()+dto.getInvolve_case_count());
				dto.setLose_case_count(EmployeeDto.getLose_case_count()+dto.getLose_case_count());
				dto.setNegotiation_case_count(EmployeeDto.getNegotiation_case_count()+dto.getNegotiation_case_count());
				dto.setNew_case_count(EmployeeDto.getNew_case_count()+dto.getNew_case_count());
				dto.setOneself_case_count(EmployeeDto.getOneself_case_count()+dto.getOneself_case_count());
				dto.setPaid_num(EmployeeDto.getPaid_num()+dto.getPaid_num());
				dto.setPtp_money(EmployeeDto.getPtp_money()+dto.getPtp_money());
				dto.setSomeone_case_count(EmployeeDto.getSomeone_case_count()+dto.getSomeone_case_count());
				dto.setTarget(EmployeeDto.getTarget()+dto.getTarget());
				dto.setTotal_case_money(EmployeeDto.getTotal_case_money()+dto.getTotal_case_money());
				dto.setName("合计");
		}
		list.add(dto);
		return super.createExcelView("statisticsEmployee", list, "所查批次统计", null, null);
	}
	

	@Override
	protected String getBaseName() {
		return "collection/statisticsEmployeePaid/";
	}

}
