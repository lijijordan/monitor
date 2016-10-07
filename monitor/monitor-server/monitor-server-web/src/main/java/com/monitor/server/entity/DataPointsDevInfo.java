package com.monitor.server.entity;

import java.io.Serializable;

/**
 * 从设备层获取当前传感器值
 * 
 * @author yinhong
 *
 */
public class DataPointsDevInfo implements Serializable {

	private static final long serialVersionUID = -4516992007701753444L;

	private Long id;

	private Long sensorid;

	private Long collecttime;

	private String value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSensorid() {
		return sensorid;
	}

	public void setSensorid(Long sensorid) {
		this.sensorid = sensorid;
	}

	public Long getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(Long collecttime) {
		this.collecttime = collecttime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}