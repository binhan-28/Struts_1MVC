package com.cs.struts.dto;

import java.sql.Date;

public class CustomerDto {
	private int customerId;
	private String customerName;
	private String sex;
	private String birthday;
	private String address;
	private Date deleteYmd;
	
	public CustomerDto(int customerId, String customerName, String sex, String birthday, String address) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.sex = sex;
		this.birthday = birthday;
		this.address = address;
	}
	public Date getDeleteYmd() {
		return deleteYmd;
	}
	public void setDeleteYmd(Date deleteYmd) {
		this.deleteYmd = deleteYmd;
	}
	public CustomerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
