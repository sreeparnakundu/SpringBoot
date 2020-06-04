package com.sree.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Approach 1: 
 * 
* If we don't want to send some field in response, we'll also can use @JsonIgnoreProperties at class level
* e.g. -> password field
*/
@JsonIgnoreProperties(value= {"field1","field2"})
public class SomeBean {

	private String field1;
	private String field2;
	/**
	 * Approach 2 --> better approach
	 * 
	* If we don't want to send some field in response, we'll use @JsonIgnore
	* e.g. -> password field
	*/
	@JsonIgnore
	private String field3;
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
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