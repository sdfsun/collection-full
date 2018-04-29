package com.kangde.collection.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kangde.collection.dto.StatisticsEntrustDto;
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.collection.service.StatisticsOrganizationService;
import com.kangde.commons.util.NumberUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;

@Controller
@RequestMapping("collection/statisticsOrganizationPaid")
public class StatisticsOrganizationPaidController extends RestfulUrlController<StatisticsOrganizationDto, String> {

	@Autowired
	private StatisticsOrganizationService statisticsService;


	/**
	 * 委托方统计展示方法
	 */
	@RequestMapping(value = "queryOrganizationPaid")
	@ResponseBody
	public SearchResult<StatisticsOrganizationDto> queryOrganization() {
		ParamCondition condition = parseCondition("*");
		String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			String[] entrustIds = entrustId.split(",");
			condition.put("entrustIds", entrustIds);
		}
		
		SearchResult<StatisticsOrganizationDto> queryCollection = statisticsService.queryOrganizationPaid(condition);

		List<StatisticsOrganizationDto> list = queryCollection.getRows();
		/*for (StatisticsOrganizationDto orgDto : list) {
			//String parentId = orgDto.getParentId();
			orgDto.setParentId(null);
		}*/
		/*List<String> queryIds = new ArrayList<String>();
	
		for (StatisticsOrganizationDto orgDto : list) {
			String parentId = orgDto.getParentId();
			queryIds.add(orgDto.getOrgId());
			
		}
		String pid = (String)condition.getParams().get("orgId");
		if(org.apache.commons.lang.StringUtils.isBlank(pid)){
			pid=SecurityUtil.getCurrentUser().getOrgId();
			if(pid==null){
				pid="O20100000-1";
			}
		}
		List<OrganizationModel> kidsIds = orgMapper.findByParentId(pid);
		ArrayList<String> allIds = new ArrayList<String>();
		for (OrganizationModel organizationModel : kidsIds) {
			String id = organizationModel.getId();
			allIds.add(id);
		}
		
		allIds.removeAll(queryIds);
		
		//获取选中根节点的所有的子节点的id
		//list3  
		//做差集
		//l4=list3-list2
		//list4遍历
		for (String string : allIds) {
			StatisticsOrganizationDto dto = new StatisticsOrganizationDto();
			dto.setOrgId(string);
			list.add(dto);
		}*/
		
		
		List<StatisticsOrganizationDto> footer = new ArrayList<StatisticsOrganizationDto>();
		StatisticsOrganizationDto dto = new StatisticsOrganizationDto();

		String org = (String) condition.getParams().get("orgId");
		for (StatisticsOrganizationDto orgDto : list) {
			String getOrgId = orgDto.getOrgId();
			orgDto.setParentId(null);
			if (getOrgId.equals(org)) {
				break;
			}
		}
		for (StatisticsOrganizationDto orgDto : list) {
			dto.setCase_count(orgDto.getCase_count()+dto.getCase_count());
			dto.setCp_money(orgDto.getCp_money()+ dto.getCp_money());
			dto.setPaid_num(orgDto.getPaid_num()+dto.getPaid_num());
			dto.setTotal_case_money(orgDto.getTotal_case_money()+dto.getTotal_case_money());
			dto.setLa_count(orgDto.getLa_count()+dto.getLa_count());
			dto.setLa_case_money(orgDto.getLa_case_money()+dto.getLa_case_money());
			//dto.setAverage_count(orgDto.getAverage_count()+dto.getAverage_count());
//			BigDecimal bd1 = new BigDecimal(dto.getTotal_case_money());
//	        BigDecimal bd2 = new BigDecimal(dto.getCase_count());
//	        BigDecimal bd3 = bd1.divide(bd2);

			dto.setCp_case_count(orgDto.getCp_case_count()+dto.getCp_case_count());
			dto.setPaid_case_count(orgDto.getPaid_case_count()+dto.getPaid_case_count());
			dto.setPtp_money(orgDto.getPtp_money()+dto.getPtp_money());
			dto.setPtp_case_count(orgDto.getPtp_case_count()+dto.getPtp_case_count());
			dto.setZlz_paid_num(orgDto.getZlz_paid_num()+dto.getZlz_paid_num());
			dto.setZlz_paid_case_count(orgDto.getZlz_paid_case_count()+dto.getZlz_paid_case_count());
		}
		dto.setPaid_count_rate("—");
		dto.setPaid_money_rate("—");
		dto.setAverage_count("—");
		dto.setPaid_rate("—");
		dto.setName("合计");

		footer.add(dto);
		queryCollection.setFooter(footer);
		return queryCollection;
	}
	
	@Override
	protected String getBaseName() {
		return "collection/statisticsOrganizationPaid/";
	}

}
