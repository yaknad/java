package com.tutorialspoint.javabasedconfiguration;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Scope;

import com.tutorialspoint.DummyClass;
import com.tutorialspoint.HelloWorld;
import com.tutorialspoint.SpellChecker;
import com.tutorialspoint.TextEditor;
import com.tutorialspoint.annotations.StudentAutowired;

@Configuration
@Import(AnotherConfig.class)
public class HelloWorldConfig {
	
   @Bean 
   @Scope("singleton") // default
   public HelloWorld helloWorld(){
      return new HelloWorld();
   }
   
   @Bean
   public SpellChecker getSpellChecker(){
	   return new SpellChecker();
   }
   
   // NOTE: see - https://stackoverflow.com/questions/21020187/in-spring-what-does-autowire-autowire-no-do
   @Bean(initMethod="init", destroyMethod="destroy"/*, name="byName!!!"*/,autowire=Autowire.NO)
   public DummyClass getDummyClass(){
	   return new DummyClass();
   }
   
   @Bean 
   public TextEditor getTextEditor(){
	   return new TextEditor(getSpellChecker(), getDummyClass());
   }
   
   @Bean
   public Integer getAge(){
	   return 120;
   }
   
   @Bean
   public String getName(){
	   return "MyName";
   }
   
   @Bean
   public StudentAutowired getStudentAutowired(){
	   return new StudentAutowired();
   }
}
