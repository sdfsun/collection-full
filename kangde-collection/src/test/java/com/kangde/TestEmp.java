package com.kangde;

import java.util.List;

import com.google.common.collect.Lists;
import com.kangde.sys.model.EmployeeInfoModel;

public class TestEmp {
	public static void main(String[] args) {
		List<EmployeeInfoModel> list = Lists.newArrayList();
		EmployeeInfoModel m = new EmployeeInfoModel();
		m.setId("1");
		list.add(m);

		m = new EmployeeInfoModel();
		m.setId("2");
		list.add(m);

		m = new EmployeeInfoModel();
		m.setId("3");
		list.add(m);
		
		m = new EmployeeInfoModel();
		m.setId("3");
		boolean contains = list.contains(m);
		System.out.println(contains);
	}

}
