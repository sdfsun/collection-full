package com.kangde.collection.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.DictionaryConst;
import com.kangde.collection.model.AddressModel;
import com.kangde.collection.model.ApproveRecordModel;
import com.kangde.commons.util.UUIDUtil;
import com.kangde.commons.vo.ParamCondition;
import com.kangde.sys.model.DictionaryModel;
import com.kangde.sys.service.DictionaryService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class CaseApprovalServiceTest {
	@Autowired
	private CaseApprovalService caseApprovalService;

	@Test
	public void save() {
		ApproveRecordModel model=new ApproveRecordModel();
		model.setId(UUIDUtil.UUID32());
		model.setCaseId("XXX");
		model.setCreateTime(new Date());
		model.setStayReason("YYYYY");
		caseApprovalService.saveApproveRecord(model);
	}


}
