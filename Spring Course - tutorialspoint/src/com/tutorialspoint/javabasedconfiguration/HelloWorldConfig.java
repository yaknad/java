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
   
   // NOTE: see - https://stackoverflow.com/questions/21020187/in-spring-what-does-autowire-autowire-no-do:
   //   Setting Autowire.NO does not mean that the bean cannot be injected in other beans via @Autowire. @Autowire works by default by type, and can also work by name using @Qualifier.
   //   So if your bean has the right type or name, it will get inject in other beans, that's normal.
   //   Autowire.NO means something like:
   //   Don't inject the properties of THIS bean being declared with @Bean neither by type or by name. If the bean properties are not set in the @Bean method code, leave them unfilled.
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
