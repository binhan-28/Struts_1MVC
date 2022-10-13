package com.cs.struts.dto;

public class UserDto {
	private int psn_cd;
	private String userID;
	private String password;
	private String userName;
	private String delete_ymd;
	
	public UserDto(int psn_cd, String userID, String password, String userName, String delete_ymd) {
		super();
		this.psn_cd = psn_cd;
		this.userID = userID;
		this.password = password;
		this.userName = userName;
		this.delete_ymd = delete_ymd;
	}

	public int getPsn_cd() {
		return psn_cd;
	}

	public void setPsn_cd(int psn_cd) {
		this.psn_cd = psn_cd;
	}

	public String getDelete_ymd() {
		return delete_ymd;
	}

	public void setDelete_ymd(String delete_ymd) {
		this.delete_ymd = delete_ymd;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public UserDto() {
		
	}
	
	public UserDto(String userID, String password) {
		this.userID = userID;
		this.password = password;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
