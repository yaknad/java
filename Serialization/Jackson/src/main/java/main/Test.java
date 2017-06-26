package main;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = CustomSerializer.class)
@JsonAutoDetect(fieldVisibility=Visibility.ANY)
public class Test {
	//@JsonIgnore(value=true)
	private String field1;
	//@JsonIgnore(value=false)
	private String field2;
	//@JsonIgnore(value=false)
	private String field3;
	
	@JsonIgnore
	private List<String> ignoredFields = new ArrayList<String>();

	public List<String> getIgnoredFields() {
		return ignoredFields;
	}

	public void setIgnoredFields(List<String> ignoredFields) {
		this.ignoredFields = ignoredFields;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
}
