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
	private String salinity;
	private String light;
	private String tds;
	private String average;

	public String getAverage() {
		return average;
	}

	public void setAverage(String average) {
		this.average = average;
	}

	public String getTds() {
		return tds;
	}

	public void setTds(String tds) {
		this.tds = tds;
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

	public String getSalinity() {
		return salinity;
	}

	public void setSalinity(String salinity) {
		this.salinity = salinity;
	}

	public String getLight() {
		return light;
	}

	public void setLight(String light) {
		this.light = light;
	}

}
