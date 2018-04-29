package com.kangde.collection.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.commons.vo.ParamCondition;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })
public class AattachServiceTest {
	@Autowired
	private AttachmentService caseAttachmentService;

	 
	@Test
	public void mulDelete() {
		String []id=new String[]{"002576dbf11e4b998a349d62319e30ab", "0801c3d9cb444d579cb447fd2f40b0f5", "0f3faccfc48b4dfb9c46efd4a391063c"};
		try {
			caseAttachmentService.mulDelete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Test
	public void getCount() {
		ParamCondition condition=new ParamCondition();
		condition.put("type", "vis");
		condition.put("businessId", "a5413227c89e4343a2e13fc49dfb1c7b");
		int queryCount = caseAttachmentService.queryCount(condition);
		System.out.println(queryCount);
	}

}
