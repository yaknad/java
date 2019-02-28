package com.tutorialspoint.annotations;

import org.springframework.beans.factory.annotation.Autowired;

public class StudentAutowiredOnFields {

	@Autowired(required=false)
	private Integer age;
	@Autowired
	private String name;

	
//	public void setAge(Integer age) {
//		this.age = age;
//	}
	public Integer getAge() {
		return age;
	}

	
//	public void setName(String name) {
//		this.name = name;
//	}
	public String getName() {
		return name;
	}
}