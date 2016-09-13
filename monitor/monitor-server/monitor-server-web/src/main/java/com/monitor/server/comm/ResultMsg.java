package com.monitor.server.comm;

import java.io.Serializable;

public class ResultMsg implements Serializable {
	private static final long serialVersionUID = 2625352254524516264L;
	private String status;
	private String message;
	private Object content;

	public ResultMsg(String status, String message, Object content) {
		this.status = status;
		this.message = message;
		this.content = content;
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

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

}