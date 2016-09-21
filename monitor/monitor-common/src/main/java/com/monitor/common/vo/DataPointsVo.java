package com.monitor.common.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DataPointsVo {

	public DataPointsVo() {
		// TODO Auto-generated constructor stub
	}

	private String deviceSn;
	private int dataType;
	private String dataTypeText;

	public String getDataTypeText() {
		return dataTypeText;
	}

	public void setDataTypeText(String dataTypeText) {
		this.dataTypeText = dataTypeText;
	}

	private String value;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date collectTime;
	private int id;
	private int sensorId;

	public String getDeviceSn() {
		return deviceSn;
	}

	public void setDeviceSn(String deviceSn) {
		this.deviceSn = deviceSn;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCollectTime() {
		return collectTime;
	}

	public void setCollectTime(Date collectTime) {
		this.collectTime = collectTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSensorId() {
		return sensorId;
	}

	public void setSensorId(int sensorId) {
		this.sensorId = sensorId;
	}

}
