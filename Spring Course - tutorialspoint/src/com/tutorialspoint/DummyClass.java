package com.tutorialspoint;

public class DummyClass {

	private int a;
	private String b;
		
	public DummyClass(int a, String b){
		System.out.println("Inside DummyClass constructor. received a=" + a + " and b=" + b);
		this.a= a;
		this.b = b;
	}
	public DummyClass() {
		System.out.println("Inside DummyClass default constructor.");
	}	
	
	// ***** For setter based dependency injection *****
	// a setter method to inject the dependency - note the naming convention: setX for injected parameter x.
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	
	
	
	public void doNothing() {
		System.out.println("Inside doing nothing. received a=" + this.a + " and b=" + this.b);
	}
		
	public void init(){
		System.out.println("init dummy class");
	}
	
	public void destroy(){
		System.out.println("destroying dummy class");
	}
	
}
