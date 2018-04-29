package com.kangde.collection.service;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.commons.web.filter.SpringUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })

public class ConfigTest {
	@Test
	public void configTest() {
		 Configuration conf = SpringUtil.getBean(PropertiesConfiguration.class);
		 String string = conf.getString("docIp");
		 System.out.println(string);
	}
}
