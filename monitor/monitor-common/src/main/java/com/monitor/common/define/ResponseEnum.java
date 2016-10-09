package com.monitor.common.define;

import java.util.HashMap;
import java.util.Map;

public enum ResponseEnum {
	SUCCESS("200", "成功"), PARAMNULL("10001", "参数不能为空"), PARAMCONVERTERROR(
			"10002", "参数转换枚举错误"), PARAMDATEFORMATERROR("10003", "日期格式不正确"), SYSEXCEPTION(
			"99999", "系统异常");

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
