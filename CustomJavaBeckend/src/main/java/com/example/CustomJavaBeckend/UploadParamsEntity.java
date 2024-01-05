package com.example.CustomJavaBeckend;

import org.springframework.web.multipart.MultipartFile;

public class UploadParamsEntity {
	
	private String sessionId;
	private int counter;
	private String fileName;
	private MultipartFile file;
	
	public int getCounter() {
		return counter;
	}
	
	
	public String getFileName() {
		return fileName;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	
	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public MultipartFile getFile() {
		return file;
	}
	
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	

}
