package com.kangde.collection.service;

import java.net.InetAddress;

import org.junit.Test;

import com.kangde.collection.call.CallCenter;


public class CallCenterTest {
	@Test
	public void testIp() {
		CallCenter callCenter=new CallCenter();
		callCenter.setPwd("123");
		callCenter.setCno("456");
		callCenter.setCustomerNumber("15711121933");
		String call = callCenter.call();
		System.out.println(call);
	}
	
	
	
}
