package com.kangde;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.kangde.collection.service.AttachmentService;
import com.kangde.commons.vo.ParamCondition;

public class AttachServiceTest {
	private AttachmentService attachmentService;
	@Before
	public void setUp() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		attachmentService = context.getBean("attachmentServiceImpl", AttachmentService.class);
	}

	@Test
	public void test() {
		ParamCondition condition=new ParamCondition();
		condition.put("type", "vis");
		int queryCount = attachmentService.queryCount(condition);
		System.out.println(queryCount);
	}

	
}
