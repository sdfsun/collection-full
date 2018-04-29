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
import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.collection.dto.VisitShowView;
import com.kangde.collection.service.StatisticsEntrustService;
import com.kangde.collection.service.StatisticsOrganizationService;
import com.kangde.collection.service.StatisticsService;
import com.kangde.collection.view.VisitDocView;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.model.VisitTemplateModel;

@Controller
@RequestMapping("collection/statisticsOrganizationOrganization")
public class StatisticsOrganizationOrganizationController extends RestfulUrlController<StatisticsEntrustDto, String> {

	@Autowired
	private StatisticsOrganizationService statisticsService;
	/**
	 * 委托方统计展示方法
	 */
	@RequestMapping(value = "queryOrganizationOrganization")
	@ResponseBody
	public SearchResult<StatisticsOrganizationDto> queryOrganization() {
		ParamCondition condition = parseCondition("*");
		String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			String[] entrustIds = entrustId.split(",");
			condition.put("entrustIds", entrustIds);
		}
		
		SearchResult<StatisticsOrganizationDto> queryCollection = statisticsService.queryOrganizationOrganization (condition);

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
			dto.setCase_count(orgDto.getCase_count() + dto.getCase_count());
			dto.setPhone_count(orgDto.getPhone_count()+dto.getPhone_count());
			dto.setVisit_count(orgDto.getVisit_count()+dto.getVisit_count());
			dto.setApply_count(orgDto.getApply_count()+dto.getApply_count());
			dto.setAssist_count(orgDto.getAssist_count()+dto.getAssist_count());
		}
		dto.setName("合计");

		footer.add(dto);
		queryCollection.setFooter(footer);
		return queryCollection;
	}
	

	
	@Override
	protected String getBaseName() {
		return "collection/statisticsOrganizationOrganization/";
	}

}
