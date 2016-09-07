package com.monitor.device.web.define;

import java.util.HashMap;
import java.util.Map;

public enum ResponseEnum {
	SUCCESS("200", "成功"), PARAMERROR("10001", "参数错误");

	private ResponseEnum(String status, String message) {
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

	// Implementing a fromString method on an enum type
	private static final Map<String, ResponseEnum> stringToEnum = new HashMap<String, ResponseEnum>();
	static {
		// Initialize map from constant name to enum constant
		for (ResponseEnum ResponseEnum : values()) {
			stringToEnum.put(ResponseEnum.toString(), ResponseEnum);
		}
	}

	// Returns ResponseEnum for string, or null if string is invalid
	public static ResponseEnum fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
