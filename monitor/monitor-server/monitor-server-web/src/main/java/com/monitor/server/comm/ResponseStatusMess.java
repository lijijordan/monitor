package com.monitor.server.comm;

import java.util.HashMap;
import java.util.Map;

public enum ResponseStatusMess {
	SUCCESS("0", "success"), FAILURE("1", "failure");

	private ResponseStatusMess(String status, String message) {
		this.status = status;
		this.message = message;
	}

	private String status;
	private String message;

	public String getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	private static final Map<String, ResponseStatusMess> stringToEnum = new HashMap<String, ResponseStatusMess>();
	static {
		for (ResponseStatusMess ResponseEnum : values()) {
			stringToEnum.put(ResponseEnum.toString(), ResponseEnum);
		}
	}

	public static ResponseStatusMess fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
