package com.tutorialspoint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tutorialspoint.annotations.Profile;
import com.tutorialspoint.annotations.Student;
import com.tutorialspoint.annotations.StudentAutowired;
import com.tutorialspoint.annotations.StudentAutowiredOnFields;
import com.tutorialspoint.annotations.TextEditorAnnotated;
import com.tutorialspoint.annotations.TextEditorAnnotatedAutowiredConstructor;
import com.tutorialspoint.annotations.TextEditorAnnotatedNoSetters;
import com.tutorialspoint.annotations.TextEditorAnnotatedWithResourceAnnotation;
import com.tutorialspoint.javabasedconfiguration.AnotherConfig2;
import com.tutorialspoint.javabasedconfiguration.HelloWorldConfig;

public class MainApp{

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Beans_no_annotations.xml");
		// ((ClassPathXmlApplicationContext) context).refresh(); -- see example for that later.
		// ((ClassPathXmlApplicationContext) context).start(); .stop();
		System.out.println("----------- Beans_no_annotations: Start -----------");

		// Beans
		System.out.println("----------- Beans -----------");

		HelloWorld obj = (HelloWorld) context.getBean("helloWorld");
		obj.setMessage("hello world - changed");
		obj.getMessage();

		//note: these beans is configured to be a singleton
		HelloWorld obj2 = (HelloWorld) context.getBean("helloWorld");		
		obj2.getMessage();


		HelloIsrael obj3 = (HelloIsrael) context.getBean("helloIsrael");
		obj3.setMessage("hello israel - changed");
		obj3.getMessage();

		HelloIsrael obj4 = (HelloIsrael) context.getBean("helloIsrael");		
		obj4.getMessage();	


		// Singleton vs. Prototype beans
		System.out.println("----------- Singleton vs. Prototype beans -----------");

		//note: this bean is configured *not* to be a singleton 	

		HelloIsrael obj5 = (HelloIsrael) context.getBean("helloIsrael2");
		obj5.setMessage("hello israel2 - changed");
		obj5.getMessage();


		HelloIsrael obj6 = (HelloIsrael) context.getBean("helloIsrael2");		
		obj6.getMessage();

		// BeanFactory b = null;   // - a basic type of application IOC container.

		// required to register to graceful shutdown events
		// casting to ClassPathXmlApplicationContext also enables this	
		((AbstractApplicationContext) context).registerShutdownHook();



		// NOTES:
		// 1. a prototype instance's destroy callback is not called - 
		//      https://stackoverflow.com/questions/15040427/spring-bean-destroy-method-singleton-and-prototype-scopes/15040600
		// 2. a prototype bean is initialized only if is requested in code (even though it's declared in Beans.xml).
		// 	  a singleton is initialized (and destroyed) even if not requested in code -  
		//    this difference is not due to a different lazy configuration! 
		// 3. because of section 2: look in code and the messages that are printed to console:
		//    first printed the initializations (and PostProcessors messages) of the singleton beans - 
		//    since they are initialized when starting the context. after that - the messages that are printed form the next lines of code -
		//    the bean.getMessage() of these singleton beans. Only after that the code is initializing the prototype bean, 
		//    and that's when it's init messages are printed. Only then the code calls the bean.gteMessage() 
		//    of the prototype bean and prints it to the console.
		// 4. controlling the order of beans initialization and destroy???
		// 

		// DI: Inner Beans
		System.out.println("----------- Dependency Injection (by setter / constructor arguments) + Inner Beans -----------");

		TextEditor te = (TextEditor) context.getBean("textEditor");
		te.spellCheck();
		te.doNothing();

		TextEditor te2 = (TextEditor) context.getBean("textEditor2");
		te2.spellCheck();
		te2.doNothing();

		// DI: collections
		System.out.println("----------- Collections -----------");

		JavaCollection jc1=(JavaCollection)context.getBean("javaCollection1");

		System.out.println("List1 size is: " + jc1.getAddressList().size());
		System.out.println("Set1 size is: " + jc1.getAddressSet().size());
		System.out.println("Map1 size is: " + jc1.getAddressMap().size());
		System.out.println("Prop1 size is: " + jc1.getAddressProp().size());		

		JavaCollection jc2=(JavaCollection)context.getBean("javaCollection2");

		System.out.println("List2 size is: " + jc2.getAddressList().size());
		System.out.println("Set2 size is: " + jc2.getAddressSet().size());
		System.out.println("Map2 size is: " + jc2.getAddressMap().size());
		System.out.println("Prop2 size is: " + jc2.getAddressProp().size());

		JavaCollection jc3=(JavaCollection)context.getBean("javaCollection3");

		System.out.println("List3 size is: " + jc3.getAddressList().size());
		System.out.println("Set3 size is: " + jc3.getAddressSet().size());
		System.out.println("Map3 size is: " + jc3.getAddressMap().size());
		System.out.println("Prop3 size is: " + jc3.getAddressProp().size());


		// autowired
		System.out.println("----------- Autowired -----------");
		TextEditor teAutowiredByName = (TextEditor) context.getBean("textEditorAutowiredByName");
		teAutowiredByName.doNothing();
		teAutowiredByName.spellCheck();

		TextEditor teAutowiredByType = (TextEditor) context.getBean("textEditorAutowiredByType");
		teAutowiredByType.doNothing();
		teAutowiredByType.spellCheck();

		TextEditor teAutowiredConstructor = (TextEditor) context.getBean("textEditorAutowiredConstructor");
		teAutowiredConstructor.doNothing();
		teAutowiredConstructor.spellCheck();

		//		TextEditor teAutowiredAutodetect = (TextEditor) context.getBean("textEditorAutowiredAutodetect");
		//		teAutowiredAutodetect.doNothing();
		//		teAutowiredAutodetect.spellCheck();


		System.out.println("----------- Beans_no_annotations: The End -----------");









		System.out.println("----------- Beans - with annotations: Start -----------");

		ApplicationContext annotationsContext = new ClassPathXmlApplicationContext("Beans.xml");
		// required to register to graceful shutdown events
		((AbstractApplicationContext) annotationsContext).registerShutdownHook();

		Student student = (Student) annotationsContext.getBean("student1");
		System.out.println("Name : " + student.getName() ); // from annotation
		System.out.println("Age : " + student.getAge() ); // from xml


		TextEditorAnnotated teAnnotated = (TextEditorAnnotated) annotationsContext.getBean("textEditorAnnotated");
		teAnnotated.spellCheck();

		TextEditorAnnotatedNoSetters teAnnotatedNoSetters = (TextEditorAnnotatedNoSetters) annotationsContext.getBean("textEditorAnnotatedNoSetters");
		teAnnotatedNoSetters.spellCheck();  

		//		// - not working! can't use the @Autowired field instead of a setter to inject to it with a setter based Dependency Injection.  			    
		//	    TextEditorAnnotatedNoSetters teAnnotatedNoSetters2 = (TextEditorAnnotatedNoSetters) annotationsContext.getBean("textEditorAnnotatedNoSetters2");
		//	    teAnnotatedNoSetters2.spellCheck();   

		TextEditorAnnotatedAutowiredConstructor teAnnotatedAutowiredConstructor = (TextEditorAnnotatedAutowiredConstructor) annotationsContext.getBean("textEditorAnnotatedAutowiredConstructor");
		teAnnotatedAutowiredConstructor.spellCheck();  

		StudentAutowired studentAutowired = (StudentAutowired) annotationsContext.getBean("studentAutowired");
		System.out.println("Name : " + studentAutowired.getName() ); 
		System.out.println("Age : " + studentAutowired.getAge() ); 

		StudentAutowiredOnFields studentAutowiredOnFields = (StudentAutowiredOnFields) annotationsContext.getBean("studentAutowiredOnFields");
		System.out.println("Name : " + studentAutowiredOnFields.getName() ); 
		System.out.println("Age : " + studentAutowiredOnFields.getAge() ); 

		// NOTES:
		// ------
		// 1. Autowired annotation is using a ByType dependency injection. If more than one bean of the required type is found,
		//	  it fallbacks to use a ByName dependency inhection!
		//	  https://stackoverflow.com/questions/30360589/does-spring-autowired-inject-beans-by-name-or-by-type

		System.out.println("profile bean:");
		Profile profile = (Profile) annotationsContext.getBean("profile");
		profile.printAge();
		profile.printName();


		TextEditorAnnotatedWithResourceAnnotation teResource = (TextEditorAnnotatedWithResourceAnnotation)annotationsContext.getBean("textEditorAnnotatedWithResourceAnnotation");
		teResource.spellCheck();

		System.out.println("----------- Beans - with annotations: End -----------");










		System.out.println("----------- Beans - java based configuration: Start -----------");

		ApplicationContext javaCtx = new AnnotationConfigApplicationContext(HelloWorldConfig.class);

		System.out.println("Before registering to context.");

		//		// load from more java configuration classes
		// NOTE: 
		// 1. in ClassPathXmlApplicationContext "register" is not possible. Only refresh.
		// 2. it works only when the first init of the context is without assigning a context class -
		//    new AnnotationConfigApplicationContext(); If we were using new AnnotationConfigApplicationContext(AAA.class);
		//    the refresh will fail. BUT - stop / start does work!
		AnnotationConfigApplicationContext javaCtx2 = new AnnotationConfigApplicationContext();
		javaCtx2.register(AnotherConfig2.class);
		System.out.println("Before refreshing context.");
		javaCtx2.refresh();
		javaCtx2.registerShutdownHook();		
		System.out.println("Refreshed context.");

		((AnnotationConfigApplicationContext)javaCtx).stop();
		((AnnotationConfigApplicationContext)javaCtx).start();

		HelloWorld helloWorld = javaCtx.getBean(HelloWorld.class);
		helloWorld.setMessage("Hello World!");
		helloWorld.getMessage();

		// getting bean from refreshed context
		Student studentFromOtherConfig = javaCtx2.getBean(Student.class);
		System.out.println(studentFromOtherConfig);

		TextEditor textEditor = javaCtx.getBean(TextEditor.class);
		textEditor.spellCheck();
		textEditor.doNothing();

		System.out.println("----------- it works with autowire annotations!!! -----------");
		// NOTE: but see - https://stackoverflow.com/questions/21020187/in-spring-what-does-autowire-autowire-no-do
		StudentAutowired student_Autowired = javaCtx.getBean(StudentAutowired.class);
		System.out.println(student_Autowired.getAge());
		System.out.println(student_Autowired.getName());

		System.out.println("----------- importing another config file into our config file -----------");
		HelloIsrael hello_Israel = javaCtx.getBean(HelloIsrael.class);
		hello_Israel.setMessage("imported config hello Israel!");
		hello_Israel.getMessage();

		System.out.println("----------- scope: prototype -----------");
		HelloIsrael hello_Israel2 = javaCtx.getBean(HelloIsrael.class);
		hello_Israel2.setMessage("setting another message to second instance");
		hello_Israel.getMessage(); // message of instance 1 not changed - It's a prototype!
		HelloWorld helloWorld2 = javaCtx.getBean(HelloWorld.class);
		helloWorld2.setMessage("Hello World 2!");
		helloWorld.getMessage(); // message of instance 1 changed - it's a singleton!


		System.out.println("----------- Beans - java based configuration: End -----------");







		System.out.println("----------- Listening to context events: Start -----------");

		// Note:
		// Spring's event handling is single-threaded so if an event is published, 
		// until and unless all the receivers get the message, 
		// the processes are blocked and the flow will not continue. 
		// Hence, care should be taken when designing your application if the event handling is to be used.

		ConfigurableApplicationContext listenedCtx = new ClassPathXmlApplicationContext("Beans_no_annotations.xml");

		// Let us raise a start event.
		listenedCtx.start();

		HelloWorld helloWorld3 = (HelloWorld) listenedCtx.getBean("helloWorld");
		helloWorld3.getMessage();

		// Let us raise a stop event.
		listenedCtx.stop();





		// stopped at:
		// https://www.tutorialspoint.com/spring/custom_events_in_spring.htm	
	}	
}
