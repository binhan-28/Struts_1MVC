package com.model;

import org.apache.struts.action.ActionForm;
/**
 * thể hiện đối tượng người dùng
 * 
 */
public class User extends ActionForm{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
}
