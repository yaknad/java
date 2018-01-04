package com.tutorialspoint;

public class HelloIsrael {

	private String message;

	public void setMessage(String message){
		this.message  = message;
	}
	public void getMessage(){
		System.out.println("Your Israel Message : " + message);
	}
	
	
	// bean life cycle
	//*****************

	public void init(){
		System.out.println("Hello Israel bean is initialized!");
	}
	
	public void destroy(){
		System.out.println("Hello Israel bean is destroyed!");
	}
}
