package com.monitor.common.define;

import java.util.HashMap;
import java.util.Map;

public enum QueryScopeEnum {
	Last60Minute, Last24Hour, Last30Day, Last3Months, LastOneYear, All;
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
