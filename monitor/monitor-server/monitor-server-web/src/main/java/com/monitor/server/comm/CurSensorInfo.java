package com.monitor.server.comm;

import java.io.Serializable;

/**
 * 各种传感器值汇总值
 * 
 * @author yinhong
 *
 */
public class CurSensorInfo implements Serializable {

	private static final long serialVersionUID = 6834622589607657812L;
	private String ph;
	private String temperature;
	private String light;
	private String ec;

	public String getPh() {
		return ph;
	}

	public void setPh(String ph) {
		this.ph = ph;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getEc() {
		return ec;
	}

	public void setEc(String ec) {
		this.ec = ec;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

}
