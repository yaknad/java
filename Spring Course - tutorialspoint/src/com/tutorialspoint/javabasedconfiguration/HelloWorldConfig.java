package com.tutorialspoint.javabasedconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tutorialspoint.HelloWorld;

@Configuration
public class HelloWorldConfig {
	
   @Bean 
   public HelloWorld helloWorld(){
      return new HelloWorld();
   }
}
