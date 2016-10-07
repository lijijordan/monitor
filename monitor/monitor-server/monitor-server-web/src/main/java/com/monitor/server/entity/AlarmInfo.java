package com.monitor.server.entity;

import java.io.Serializable;

/**
 * 
 * 告警信息实体
 * 
 * @author yinhong
 *
 */
public class AlarmInfo implements Serializable {

	private static final long serialVersionUID = 54320056261584713L;

	private int id;
	private String userAccount;
	private String devSN;
	private String sensorType;
	private String maxThreshold;
	private String minThreshold;
	private String switchStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getDevSN() {
		return devSN;
	}

	public void setDevSN(String devSN) {
		this.devSN = devSN;
	}

	public String getSensorType() {
		return sensorType;
	}

	public void setSensorType(String sensorType) {
		this.sensorType = sensorType;
	}

	public String getMaxThreshold() {
		return maxThreshold;
	}

	public void setMaxThreshold(String maxThreshold) {
		this.maxThreshold = maxThreshold;
	}

	public String getMinThreshold() {
		return minThreshold;
	}

	public void setMinThreshold(String minThreshold) {
		this.minThreshold = minThreshold;
	}

	public String getSwitchStatus() {
		return switchStatus;
	}

	public void setSwitchStatus(String switchStatus) {
		this.switchStatus = switchStatus;
	}

}
