package com.monitor.server.comm;

import java.io.Serializable;

/**
 * 各种传感器值汇总值
 * 
 * @author yinhong
 *
 */
public class SensorInfo implements Serializable {

	private static final long serialVersionUID = 6834622589607657812L;
	private String ph;
	private String temperature;
	private String salinity;
	private String light;
	private String tds;

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
