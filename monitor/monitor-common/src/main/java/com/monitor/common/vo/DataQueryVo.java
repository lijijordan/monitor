package com.monitor.common.vo;

import java.util.Date;

public class DataQueryVo {

	public DataQueryVo() {
		// TODO Auto-generated constructor stub
	}

	private String sn;
	private String deviceSn;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

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

	private int dataType;
	private int queryScope;
	private Date startTime;
	private Date endTime;

	public int getQueryScope() {
		return queryScope;
	}

	public void setQueryScope(int queryScope) {
		this.queryScope = queryScope;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

}
