package com.tutorialspoint.javabasedconfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.tutorialspoint.HelloIsrael;

@Configuration
public class AnotherConfig {
	
   @Bean
   @Scope("prototype")
   public HelloIsrael getHelloIsrael(){
	   return new HelloIsrael();
   }
}
