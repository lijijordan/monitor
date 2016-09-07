package com.monitor.device.web.common;

public class ResponseVo<T> {
	private String status;
	private String message;
	private T content;
	public ResponseVo() {
		this.message = "";
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getContent() {
		return content;
	}
	public void setContent(T content) {
		this.content = content;
	}
}
