package com.kangde.collection.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.collection.model.CaseLinkmanModel;
import com.kangde.commons.util.UUIDUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class LinkManServiceTest {
	@Autowired
	private CaseLinkmanService caseLinkmanService;

	@Test
	public void save() {

		CaseLinkmanModel model = new CaseLinkmanModel();

		model.setId(UUIDUtil.UUID32());
		model.setPhone("15711121933");
		model.setPhoneType("1");
		model.setName("李登文");
		model.setCaseId("XXXXXXXXXXXX");
		model.setCreateTime(new Date());
		model.setCreateEmpId("admin");
		caseLinkmanService.save(model);
	}

}
