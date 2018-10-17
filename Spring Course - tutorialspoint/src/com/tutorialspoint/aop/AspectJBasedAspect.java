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
	public Object doAroundTask(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("AspectJBasedAspect: Around - Student method has been called - now calling it...");
		
		Object value;
		try{
			value = pjp.proceed();
			System.out.println("Around - called method returned a VALUE: " + value);
		}
		catch(Exception ex){
			System.out.println("Around - called method threw an EXCEPTION: " + ex.getMessage());
			throw ex;
		}
		// Note: Around is the Advice that is called before all other Advices - both in the "before" phase and in the "after" phase, Around is th e first Advice to be called.
		// At this point, the original called method (the joinpoint) is called, and the returned value is returned by the proceed() call.
		// Here we may decide to alter the returned value.
		// We could also no use the proceed() call (and even not declare the ProceedingJoinPoint parameter at all), and the original joinpoint would not have been called at all!!
		// Note: using "void" as the return value of the @Around Advice, even with pjp.proceed, will cause the final return value of the original called method to be null!!!
		// Using a specific type instead of object will use this Around Advice only for methods that return the specific type?
		// To be used for a specific return type - change the pointcut - e.g.: "execution(public java.String com.tutorialspoint.aop.*.*(..))"
		// To see more options of controlling the returned value:
		// https://stackoverflow.com/questions/15781322/joinpoint-vs-proceedingjoinpoint-in-aop-using-aspectj
		
		// Note: that all Xml based Advices are called before all the annotated "before" Advices and after the annotated "after" Advices. 
		return value;
	}
	
}
