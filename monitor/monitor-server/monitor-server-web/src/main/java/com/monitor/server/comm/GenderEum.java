/**
 * 性别
 */
package com.monitor.server.comm;

/**
 * @author yinhong
 *
 */
public enum GenderEum {

	MAN("MAN", 1), WOMEN("WOMEN", 2);

	private String name;
	private int value;

	private GenderEum(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (GenderEum g : GenderEum.values()) {
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