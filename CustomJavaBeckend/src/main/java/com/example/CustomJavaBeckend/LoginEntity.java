package com.example.CustomJavaBeckend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


public class LoginEntity {

	
	private String login;
	

	private String password;
	
	public LoginEntity() {
		
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
