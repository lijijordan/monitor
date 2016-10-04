package com.monitor.server.entity;

import java.util.Date;

public class DataPointInfo {

	private Date collecttime;

	private String value;

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

}