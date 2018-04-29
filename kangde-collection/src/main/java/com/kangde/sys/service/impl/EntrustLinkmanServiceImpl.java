package com.kangde.sys.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.kangde.commons.easyui.Combobox;
import com.kangde.commons.service.AbstractService;
import com.kangde.sys.dto.EntrustLinKmanDto;
import com.kangde.sys.mapper.EntrustCaseProductReleationMapper;
import com.kangde.sys.mapper.EntrustLinkmanMapper;
import com.kangde.sys.mapper.EntrustProductMapper;
import com.kangde.sys.model.EntrustCaseProductReleation;
import com.kangde.sys.model.EntrustLinkman;
import com.kangde.sys.model.EntrustModel;
import com.kangde.sys.service.EntrustLinkmanService;

/**
 * 委托方 Service实现类
 * 
 * @author zhangyj
 */
@Service("entrustLinkmanService")
public class EntrustLinkmanServiceImpl extends AbstractService<EntrustLinKmanDto,String> implements EntrustLinkmanService {
	@Autowired
	private EntrustLinkmanMapper entrustmapper;
/*	@Autowired
	private EntrustProductMapper entrustProductMapper;*/
	@Autowired
	private EntrustCaseProductReleationMapper caseProductReleationMapper;
	
	   public List<Combobox> combobox(@RequestParam(value="entrustNameValue") String entrustNameValue){
		   List<Combobox> resultList = new ArrayList<>();
		   EntrustCaseProductReleation model=new EntrustCaseProductReleation();
		   String complete=null;
		   
		  List<EntrustCaseProductReleation> list= caseProductReleationMapper.findByEntrustId(entrustNameValue);
		  for (int i = 0; i < list.size(); i++) {
			  model=list.get(i);
			  complete=model.getCaseTypeName()+","+model.getHandleName()+","+model.getEntrustName()+","+model.getCode();
			  Combobox combobox1 = new Combobox(model.getProductId(),complete);
			   resultList.add(combobox1);
		}
		    return resultList;
	   
	   }
	
}
