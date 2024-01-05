package com.example.CustomJavaBeckend;

public class DownloadEntity {
	
	private String sessionId;
	private String outputFileName;
	
	public String getOutputFileName() {
		return outputFileName;
	}
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setOutputFileName(String outputFileName) {
		this.outputFileName = outputFileName;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	

}
