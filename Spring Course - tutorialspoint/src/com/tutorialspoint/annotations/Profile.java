package com.tutorialspoint.annotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Profile {

	@Autowired
	@Qualifier("student2") // without this - there's an ambiguity between two Student typed beans (unless the name of one of them is "student" - like the fields name)! 
	private Student student;

	public Profile(){
		System.out.println("Inside Profile constructor." );
	}
	public void printAge() {
		System.out.println("Age : " + student.getAge() );
	}
	public void printName() {
		System.out.println("Name : " + student.getName() );
	}
}
