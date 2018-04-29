package com.kangde.demo.service.impl;

import org.springframework.stereotype.Service;

import com.kangde.commons.service.AbstractService;
import com.kangde.demo.model.StudentModel;
import com.kangde.demo.service.StudentService;

@Service("studentService")
public class StudentServiceImpl extends AbstractService<StudentModel,String> implements StudentService{
	
}
