package com.emerick.backend.model;

public class ErrorMessage {
	private int status;
	private String message;

	public ErrorMessage(String error, int status) {
		super();
		this.message = error;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String error) {
		this.message = error;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
