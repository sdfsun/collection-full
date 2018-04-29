package com.kangde.collection.service;

import java.net.InetAddress;

import org.junit.Test;

public class IPTest {
	@Test
	public void testIp() {
		String ip = getLocalIp();
		System.out.println(ip);
	}
	
	
	private String getLocalIp() {
		String localIp = "";
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			localIp = address.getHostAddress();
		} catch (Exception e) {
			
		}
		return localIp;
	}
	
	
}
