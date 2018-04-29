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

import com.kangde.collection.dto.StatisticsOrganizationDto;
import com.kangde.collection.service.StatisticsOrganizationService;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.commons.vo.SearchResult;
import com.kangde.commons.web.controller.RestfulUrlController;
import com.kangde.sys.mapper.OrganizationMapper;

@Controller
@RequestMapping("collection/statisticsOrganization")
public class StatisticsOrganizationController extends RestfulUrlController<StatisticsOrganizationDto, String> {

	private static final String String = null;
	@Autowired
	private StatisticsOrganizationService statisticsService;
	@Autowired
	private OrganizationMapper orgMapper;

	/**
	 * 风控方统计展示方法
	 */
	@RequestMapping(value = "queryOrganization")
	@ResponseBody
	public SearchResult<StatisticsOrganizationDto> queryOrganization() {
		ParamCondition condition = parseCondition("*");
		String entrustId=condition.get("entrustId");
		if (StringUtils.isNotBlank(entrustId)) {
			String[] entrustIds = entrustId.split(",");
			condition.put("entrustIds", entrustIds);
		}
		
		SearchResult<StatisticsOrganizationDto> queryCollection = statisticsService.queryCollection(condition);

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
			dto.setCp_case_count(orgDto.getCp_case_count()+dto.getCp_case_count());
			dto.setPaid_case_count(orgDto.getPaid_case_count()+dto.getPaid_case_count());
			dto.setCase_count(orgDto.getCase_count() + dto.getCase_count());
			dto.setCp_money(orgDto.getCp_money() + dto.getCp_money());
			dto.setPaid_num(orgDto.getPaid_num() + dto.getPaid_num());
			dto.setTotal_case_money(orgDto.getTotal_case_money() + dto.getTotal_case_money());
			dto.setCp_back_paid(orgDto.getCp_back_paid()+dto.getCp_back_paid());
			dto.setBack_paid(orgDto.getBack_paid()+dto.getBack_paid());
		}
		dto.setAverage_rate("—");
		dto.setHun_average_out("—");
		dto.setPart_average_out("—");
		dto.setAverage_count("—");
		dto.setName("合计");

		footer.add(dto);
		queryCollection.setFooter(footer);
		return queryCollection;
	}

	/** 导出所选分公司统计 */
	@RequestMapping(value = "/exportSelected")
	public ModelAndView exportSelected(String[] ids) {
		List<StatisticsOrganizationDto> list = statisticsService.queryExport(Arrays.asList(ids));
		return super.createExcelView("statisticsOrganization", list, "所选分公司统计", null, null);
	}

	/** 导出所查委分公司统计 */
	@RequestMapping(value = "/exportQueryExcel", method = RequestMethod.POST)
	public ModelAndView exportExcel() {
		ParamCondition condition = parseCondition("*");
		List<StatisticsOrganizationDto> list = statisticsService.exportQueryExcel(condition);
		StatisticsOrganizationDto dto = new StatisticsOrganizationDto();
		for (StatisticsOrganizationDto orgDto : list) {

			dto.setAchieve(orgDto.getAchieve() + dto.getAchieve());
			dto.setCase_count(orgDto.getCase_count() + dto.getCase_count());
			dto.setCp_money(orgDto.getCp_money() + dto.getCp_money());
			dto.setFamily_case_count(orgDto.getFamily_case_count() + dto.getFamily_case_count());
			dto.setInvolve_case_count(orgDto.getInvolve_case_count() + dto.getInvolve_case_count());
			dto.setLose_case_count(orgDto.getLose_case_count() + dto.getLose_case_count());
			dto.setNegotiation_case_count(orgDto.getNegotiation_case_count() + dto.getNegotiation_case_count());
			dto.setNew_case_count(orgDto.getNew_case_count() + dto.getNew_case_count());
			dto.setOneself_case_count(orgDto.getOneself_case_count() + dto.getOneself_case_count());
			dto.setPaid_num(orgDto.getPaid_num() + dto.getPaid_num());
			dto.setPtp_money(orgDto.getPtp_money() + dto.getPtp_money());
			dto.setSomeone_case_count(orgDto.getSomeone_case_count() + dto.getSomeone_case_count());
			dto.setTarget(orgDto.getTarget() + dto.getTarget());
			dto.setTotal_case_money(orgDto.getTotal_case_money() + dto.getTotal_case_money());
			dto.setName("合计");
		}

		list.add(dto);
		return super.createExcelView("statisticsOrganization", list, "所查分公司统计", null, null);
	}
	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @RequestMapping(value = "getOrganizationTreeJoinAttachedOrgs")
	 * 
	 * @ResponseBody public List<TreeNode>
	 * getOrganizationTreeJoinAttachedOrgs(@RequestParam(value="selectType",
	 * required=false) String selectType) { List<TreeNode> titleList = null;
	 * if(StringUtils.isNotBlank(selectType)){ //---请选择---" SelectType s =
	 * SelectType.getSelectTypeValue(selectType); TreeNode selectTreeNode = new
	 * TreeNode("", s.getDescription()); titleList =
	 * Lists.newArrayList(selectTreeNode); } List<TreeNode> treeNodes =
	 * statisticsService.getOrganizationTreeJoinAttachedOrgs(SecurityUtil.
	 * getCurrentUser()); if(titleList!=null){ return ListUtils.union(titleList,
	 * treeNodes); } return treeNodes; }
	 */

	@Override
	protected String getBaseName() {
		return "collection/statisticsOrganization/";
	}

}
