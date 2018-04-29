package com.kangde.collection.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.collection.model.VisitRecordModel;
import com.kangde.commons.util.UUIDUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class VisitRecordServiceTest {
	@Autowired
	private VisitRecordService visitRecordService;

	 @Before  
	    public void setUp() {  
//	        notifyService = new NotifyService();  
//	        uc = mock(UserCenter.class);  
//	        mc = mock(MessageCenter.class);  
//	        notifyService.setUc(uc);  
//	        notifyService.setMc(mc);  
	    }  
	 
	@Test
	public void save() {
		VisitRecordModel m=new VisitRecordModel();
		m.setAddressId(UUIDUtil.UUID32());
		m.setAge(10);
		//m.setApplyEmpId(UUIDUtil.UUID32());
		m.setCaseId(UUIDUtil.UUID32());
		m.setId(UUIDUtil.UUID32());
		m.setRequire("999");
		visitRecordService.save(m);
	}
	@Test
	public void query() {
		//visitRecordService.queryForPage(condition)
	}
	@Test
	public void appplyToVisit() {
		VisitRecordModel model=new VisitRecordModel();
		model.setAge(999);
		visitRecordService.applyToVisit(model, "1");
	}
}
