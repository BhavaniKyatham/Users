package com.example.user.dto;

import javax.validation.constraints.NotEmpty;

public class Credentials {
	@NotEmpty(message="username should not be empty")
	private String userName;
	@NotEmpty(message="password should not be empty")
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
