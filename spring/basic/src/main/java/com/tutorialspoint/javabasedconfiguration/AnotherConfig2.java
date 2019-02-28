package com.tutorialspoint.javabasedconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tutorialspoint.annotations.Student;

@Configuration
public class AnotherConfig2 {
	
   @Bean
   public Student getStudent(){
	   return new Student();
   }
}
