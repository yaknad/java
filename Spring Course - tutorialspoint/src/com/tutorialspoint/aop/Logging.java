package com.tutorialspoint.aop;

public class Logging {

	/** 
	 * This is the method which I would like to execute
	 * before a selected method execution.
	 */
	public void beforeAdvice(){
		System.out.println("XmlBasedAspect: Going to setup student profile.");
	}
	
	public void beforeAdvice2(){
		System.out.println("XmlBasedAspect: Now using beforeAdvice2 method.");
	}

	/** 
	 * This is the method which I would like to execute
	 * after a selected method execution.
	 */
	public void afterAdvice(){
		System.out.println("XmlBasedAspect: Student profile has been setup.");
	}

	/** 
	 * This is the method which I would like to execute
	 * when any method returns.
	 */
	public void afterReturningAdvice(Object retVal) {
		System.out.println("XmlBasedAspect: Returning:" + (retVal != null ? retVal.toString() : "null"));
	}

	/**
	 * This is the method which I would like to execute
	 * if there is an exception raised.
	 */
	public void AfterThrowingAdvice(IllegalArgumentException ex){
		System.out.println("XmlBasedAspect: There has been an exception: " + ex.toString());   
	}
	
}
