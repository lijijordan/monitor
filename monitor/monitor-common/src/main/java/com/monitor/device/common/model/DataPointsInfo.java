package com.monitor.device.common.model;

import java.util.Date;

public class DataPointsInfo {

	public DataPointsInfo() {
		// TODO Auto-generated constructor stub
	}
	
	private long id;
	private long sensorid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getSensorid() {
		return sensorid;
	}
	public void setSensorid(long sensorid) {
		this.sensorid = sensorid;
	}
	public Date getCollecttime() {
		return collecttime;
	}
	public void setCollecttime(Date collecttime) {
		this.collecttime = collecttime;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	private Date collecttime;
	private String value;

}
