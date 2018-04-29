package com.kangde.collection.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kangde.commons.web.filter.SpringUtil;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:applicationContext.xml" })

public class DateTest {
	@Test
	public void dateTest() {
		 Date add30Days = this.add30Days(new Date());
		 System.out.println(add30Days+"-------------------------------");
	}
	
	
    Date add30Days(Date caseBackdate) {
		Calendar   calendar   =   new   GregorianCalendar(); 
		     calendar.setTime(caseBackdate); 
		     calendar.add(calendar.DATE,30);//把日期往后增加一天.整数往后推,负数往前移动 
		   return calendar.getTime();   //这个时间就是日期往后推一天的结果 
	}
}
