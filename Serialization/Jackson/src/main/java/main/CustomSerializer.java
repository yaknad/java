package main;

import java.io.IOException;
import java.lang.reflect.Field;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomSerializer extends StdSerializer<Test> {

	public CustomSerializer() {
        this(null);
    }
   
    public CustomSerializer(Class<Test> t) {
        super(t);
    }
    
	protected CustomSerializer(Class<?> arg0, boolean arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void serialize(Test test1, JsonGenerator jgen, SerializerProvider arg2)
			throws IOException, JsonGenerationException {

		jgen.writeStartObject();

		
		
		Class<Test> testClass = (Class<Test>) test1.getClass();
		Field[] fields = testClass.getDeclaredFields();
		System.out.println("Fields: \n");
		for (Field field : fields) {
			field.setAccessible(true);
			String name = field.getName();
			
			if(test1.getIgnoredFields().contains(name) ||
					field.getAnnotation(JsonIgnore.class) != null){
				continue;
			}
			try {
				String value1 = field.get(test1).toString();
				if(value1 == null){
					value1 = "null";
				}
				jgen.writeStringField(name, value1);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		jgen.writeEndObject();
		
			
			
		
	}

}
