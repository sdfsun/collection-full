package com.kangde.collection.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kangde.DictionaryConst;
import com.kangde.collection.HurryRecordConst;
import com.kangde.collection.service.CaseService;
import com.kangde.collection.service.HurryRecordService;
import com.kangde.collection.util.OperationUtil;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.service.DictionaryService;
import com.kangde.sys.util.DictUtil;
@Service
public class CaseColorService {
	@Autowired
	private DictionaryService dictionaryService;
	@Autowired
	private CaseService caseService;
	@Autowired
	private HurryRecordService hurryRecordService;
	public Map<String, String> getColorMap() {
		List<DictionaryModel> dicts = dictionaryService.findSubsByPath(DictUtil.getPathValue("CASE_COLOR"));
		Map<String, String> colorMap = new HashMap<String, String>();
		for (DictionaryModel dictionaryModel : dicts) {
			String key=dictionaryModel.getDictKey();
			colorMap.put(dictionaryModel.getValue(),"<span style='color:"+key+"'>"+dictionaryModel.getRemark()+"<span>");
		}
		return colorMap;
	}
	
	public void updateCaseColor(String []ids, String color) {
		
		
		if(ids!=null && color!=null){
			caseService.updateColor(ids, color);
			
			//保存操作记录  bianbianbian
			List<DictionaryModel> dicts = dictionaryService.findSubsByPath(DictionaryConst.CASE_COLOR);
			String colorText="";
			for (DictionaryModel dictionaryModel : dicts) {
				if(color.equals(dictionaryModel.getValue())){
					colorText=dictionaryModel.getRemark();
					break;
				}
			}
			for (String caseId : ids) {
				String content=" [ "+colorText+"]";
				hurryRecordService.save(OperationUtil.addRecord(HurryRecordConst.color, caseId, "案件标色", content, caseId,HurryRecordConst.noaction));
			}
		}
	}

}
