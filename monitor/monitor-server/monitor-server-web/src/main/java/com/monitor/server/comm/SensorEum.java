/**
 * 传感器
 */
package com.monitor.server.comm;

/**
 * @author yinhong
 *
 */
public enum SensorEum {

	CONDUCTIVITY("CONDUCTIVITY", 1), PH("PH", 2), TEMPERATURE("TEMPERATURE", 3), WATERLINE("WATERLINE", 4);

	private String name;
	private int value;

	private SensorEum(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (SensorEum g : SensorEum.values()) {
			if (g.getValue() == value) {
				return g.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}