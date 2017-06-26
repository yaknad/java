package main;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;


public class JacksonTest {

	public static void main(String[] args) throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, NoSuchMethodException {
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		
		Test test1 = new Test();
		test1.setField1("aaa");
		test1.setField2("bbb");
		test1.setField3("ccc");
		
		String json = objectMapper.writeValueAsString(test1);
		System.out.println("First Object\n"+json);
		
		test1.getIgnoredFields().add("field1");
		json = objectMapper.writeValueAsString(test1);
		System.out.println("Second Object\n"+json);
		
//		Class<Test> testClass = (Class<Test>) test1.getClass();
//		Field[] fields = testClass.getDeclaredFields();
//		System.out.println("Fields: \n");
//		for (Field field : fields) {
//			System.out.println(field.getName().toString());
//			Annotation[] annotations = field.getDeclaredAnnotations();
//			for (Annotation annotation : annotations) {
//				System.out.println("before change: " + annotation.toString());
//				Class<Annotation> annClass = (Class<Annotation>) annotation.getClass();
//				Method method1 = annClass.getMethod("value", null);
//				Class<?>[] paramsArr = {Boolean.class};
//				Method method2 = annClass.getMethod("value", paramsArr);
//				Field[] annFields = annClass.getDeclaredFields();
//				for (Field field2 : annFields) {
//					System.out.println("ann fields");
//					System.out.println(field2.get(field2));
//				}
////				annClass.getField("value").set(annotation, false);
////				System.out.println(annotation.toString());
////				System.out.println("after change: " + annotation.toString());
//			}
		}

		
//		//convert Object to json string
//		Employee emp1 = createEmployee();
//		//configure Object mapper for pretty print
//		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
//		
//		//writing to console, can write to any output stream such as file
//		StringWriter stringEmp = new StringWriter();
//		objectMapper.writeValue(stringEmp, emp1);
//		System.out.println("Employee JSON is\n"+stringEmp);
//	}
}

