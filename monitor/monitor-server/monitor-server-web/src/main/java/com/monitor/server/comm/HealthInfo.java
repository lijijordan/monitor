package com.monitor.server.comm;

import java.io.Serializable;

/**
 * 各种健康度值汇总值
 * 
 * @author yinhong
 *
 */
public class HealthInfo implements Serializable {

	private static final long serialVersionUID = -3012865911439119568L;
	private String ph;
	private String temperature;
	private String ec;
	private String light;
	private String average;

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

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
