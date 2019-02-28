package com.tutorialspoint;

public class HelloWorld /*implements InitializingBean, DisposableBean */{
	
	private String message;

	public void setMessage(String message){
		this.message  = message;
	}
	public void getMessage(){
		System.out.println("Your World Message : " + message);
	}
	
	
	// bean life cycle
	//*****************

//	The init-method attribute specifies a method that is to be called on the bean immediately upon instantiation. 
//	Similarly, destroymethod specifies a method that is called just before a bean is removed from the container.
	
	public void init(){
		System.out.println("HelloWorld bean is initialized!");
	}
	
	public void destroy(){
		System.out.println("HelloWorld bean is destroyed!");
	}
	
}
