package com.tutorialspoint.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AspectJBasedAspect {

	@Pointcut("execution(* com.tutorialspoint.aop.*.*(..))") // pointcut expression 
	private void allMethods() {}  // pointcut "signature"
	
	@Pointcut("execution(* com.tutorialspoint.aop.*.getName(..))") // pointcut expression 
	private void getNamePointut() {}  // pointcut "signature"
	
	@Pointcut("execution(* com.tutorialspoint.aop.*.setName(..))") // pointcut expression 
	private void setNamePointut() {}  // pointcut "signature"
	
	@Pointcut("execution(* com.tutorialspoint.aop.*.setName(..)) || execution(* com.tutorialspoint.aop.*.getName(..))") // pointcut expression 
	private void setNameOrGetNamePointut() {}  // pointcut "signature"
	
	//-----------
	
	@Before("allMethods()")
	public void beforeAdvice(){
		System.out.println("AspectJBasedAspect: Going to setup student profile.");
	}

	@After("allMethods()")
	public void afterAdvice(){
		System.out.println("AspectJBasedAspect: Student profile has been setup.");
	}

	@AfterReturning(pointcut = "allMethods()", returning = "retVal")
	public void afterReturningAdvice(Object retVal) {
		System.out.println("AspectJBasedAspect: Returning:" + (retVal != null ? retVal.toString() : "null"));
	}

	@AfterThrowing(pointcut = "allMethods()", throwing = "ex")
	public void AfterThrowingAdvice(IllegalArgumentException ex){
		System.out.println("AspectJBasedAspect: There has been an exception: " + ex.toString());   
	}
	
//	@Around("allMethods()")
//	public void doAroundTask(ProceedingJoinPoint pjp) throws Throwable{
//		System.out.println("AspectJBasedAspect: Student method has been called / returned/ threw an excpetion (Around Advice)");
//		
//		// ------------- NOTE: if stopping the code here, the original method call is stopped!
//		// ------------- NOTE: using pjp.proceed(), will continue to the original method (and other Advices of it), but the passed on value is null!
//		pjp.proceed();
//		// -------------- In order to pass on the original returned value: use the syntax in "doAroundTast2" Advice!
//		
//	}
	
	@Around("allMethods()")
	public void doAroundTask2(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("AspectJBasedAspect: Student method has been called / returned/ threw an excpetion (Around Advice)");
		
		// ------------- NOTE: if stopping the code here, the original method call is stopped!
		// ------------- NOTE: using pjp.proceed(), will continue to the original method (and other Advices of it), but the passed on value is null!
		pjp.proceed();
		
	}
	
}
