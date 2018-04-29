package com.kangde.demo.model;

import com.kangde.commons.model.BaseModel;

/**
 * 测试学生类
 * @author lisuo
 *
 */
@SuppressWarnings("serial")
public class StudentModel extends BaseModel<String>{
	
	private String name;
	private Integer age;
	private String studentNo;
	private String createUser;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	
}
