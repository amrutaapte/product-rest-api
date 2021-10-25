package com.product.main.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

public class APIError {
	
	
	private HttpStatus status;
	private String message;
	private LocalDateTime timeStamp;
	
	public APIError() {
		super();
	}
	
	public APIError(HttpStatus status, String message, LocalDateTime timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "APIError [status=" + status + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

}
