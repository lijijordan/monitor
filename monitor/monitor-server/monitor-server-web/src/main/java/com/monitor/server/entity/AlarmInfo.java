package com.monitor.server.entity;

import java.io.Serializable;
import java.util.Date;

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
	private int sensorType;
	private int currentVal;
	private int threshold;
	private String advice;
	private Date createTime;

	private UserInfo userInfo;
	private EquInfo equInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSensorType() {
		return sensorType;
	}

	public void setSensorType(int sensorType) {
		this.sensorType = sensorType;
	}

	public int getCurrentVal() {
		return currentVal;
	}

	public void setCurrentVal(int currentVal) {
		this.currentVal = currentVal;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String getAdvice() {
		return advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public EquInfo getEquInfo() {
		return equInfo;
	}

	public void setEquInfo(EquInfo equInfo) {
		this.equInfo = equInfo;
	}

}
