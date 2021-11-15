package com.flight.management.models;

import java.time.LocalDateTime;

public class ErrorMessage {
	
	private String message;
	private LocalDateTime time;
	private int status;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ErrorMessage [message=" + message + ", time=" + time + ", status=" + status + "]";
	}
	public ErrorMessage(String message, LocalDateTime time, int status) {
		super();
		this.message = message;
		this.time = time;
		this.status = status;
	}
	public ErrorMessage() {
		super();
	}
	
}
