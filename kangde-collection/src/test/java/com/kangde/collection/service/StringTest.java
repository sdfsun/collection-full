package com.kangde.collection.service;

import java.io.File;
import java.util.Collection;

import org.junit.Test;


public class StringTest {
	@Test
	public void subString() {
		String s="abc/12345";
		String substring = s.substring(0,5);
		System.out.println(substring);
	}
}
