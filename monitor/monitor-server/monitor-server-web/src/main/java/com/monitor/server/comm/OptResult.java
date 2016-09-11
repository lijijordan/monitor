/**
 * 操作结果
 */
package com.monitor.server.comm;

/**
 * @author yinhong
 *
 */
public enum OptResult {

	SUCCESS("SUCCESS", 1), FAIL("FAIL", 2);

	private String name;
	private int value;

	private OptResult(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static String getName(int value) {
		for (OptResult g : OptResult.values()) {
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