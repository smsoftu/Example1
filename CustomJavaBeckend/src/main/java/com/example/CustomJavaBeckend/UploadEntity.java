package com.example.CustomJavaBeckend;

public class UploadEntity {

	private int id;
	private String fileName;
	private byte[] content;
	
	public byte[] getContent() {
		return content;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public int getId() {
		return id;
	}
	
	public void setContent(byte[] content) {
		this.content = content;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
}
