package com.example.CustomJavaBeckend;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;


public class LimitEntity {
	
	private String login;
	private String password;
	private int limit;
	private String sessionId;
	
	public int getLimit() {
		return limit;
	}
	
	public String getLogin() {
		return login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public LimitEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	

}
