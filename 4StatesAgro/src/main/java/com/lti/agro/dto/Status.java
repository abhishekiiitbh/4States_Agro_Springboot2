package com.lti.agro.dto;

public class Status {
	
	private StatusType status;
    private String message;
    private int id;
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static enum StatusType {
        SUCCESS, FAILURE;
    }

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}
