/**
 * 操作对象
 */
package com.monitor.server.comm;

/**
 * @author yinhong
 *
 */
public enum OptObject {

	USER("USER", 1), EQU("EQU", 2), USEREQU("USEREQU", 3), SENSOR("SENSOR", 4);

	private String name;
	private int value;

	private OptObject(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (OptObject g : OptObject.values()) {
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