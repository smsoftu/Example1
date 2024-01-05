package com.example.CustomJavaBeckend;

public class Resp {
	
	private String status;
	private String role;
	private int limit;
	private int userId;
	private String sessionId;
	
	public Resp(String status,
				String role,
				int limit,
				int userId,
				String sessionId
	) {
		this.status=status;
		this.role=role;
		this.limit=limit;
		this.userId=userId;
		this.sessionId=sessionId;
	}
	
	
	public String getSessionId() {
		return sessionId;
	}
	
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	public int getLimit() {
		return limit;
	}
	
	public String getRole() {
		return role;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}

}
