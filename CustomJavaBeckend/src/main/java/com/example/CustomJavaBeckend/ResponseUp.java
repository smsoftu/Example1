package com.example.CustomJavaBeckend;

public class ResponseUp {
	private String message;
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ResponseUp(String message) {
		this.message=message;
	}
}
