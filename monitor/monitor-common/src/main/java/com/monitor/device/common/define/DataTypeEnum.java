package com.monitor.device.common.define;

import java.util.HashMap;
import java.util.Map;

public enum DataTypeEnum {
	Temperature, PH, WaterLine, Conductivity;
	// Implementing a fromString method on an enum type
	private static final Map<String, DataTypeEnum> stringToEnum = new HashMap<String, DataTypeEnum>();
	static {
		// Initialize map from constant name to enum constant
		for (DataTypeEnum DataTypeEnum : values()) {
			stringToEnum.put(DataTypeEnum.toString(), DataTypeEnum);
		}
	}

	// Returns DataTypeEnum for string, or null if string is invalid
	public static DataTypeEnum fromString(String symbol) {
		return stringToEnum.get(symbol);
	}
}
