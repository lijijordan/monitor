package com.monitor.common.define;

import java.util.HashMap;
import java.util.Map;

public enum QueryScopeEnum {
	Day(1, "Day"), Week(2, "Week"), Month(3, "Month");

	private int key;
	private String val;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	QueryScopeEnum(int key, String val) {
		this.key = key;
		this.val = val;
	}

	// Implementing a fromString method on an enum type
	private static final Map<String, QueryScopeEnum> stringToEnum = new HashMap<String, QueryScopeEnum>();
	static {
		// Initialize map from constant name to enum constant
		for (QueryScopeEnum QueryScopeEnum : values()) {
			stringToEnum.put(QueryScopeEnum.toString(), QueryScopeEnum);
		}
	}

	// Returns QueryScopeEnum for string, or null if string is invalid
	public static QueryScopeEnum fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
