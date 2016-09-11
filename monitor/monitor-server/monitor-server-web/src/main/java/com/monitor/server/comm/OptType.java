/**
 * 操作类型
 */
package com.monitor.server.comm;

/**
 * @author yinhong
 *
 */
public enum OptType {

	CREATE("CREATE", 1), UPDATE("UPDATE", 2), DELETE("DELETE", 3);

	private String name;
	private int value;

	private OptType(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (OptType g : OptType.values()) {
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