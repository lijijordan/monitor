package com.monitor.server.comm;

import java.io.Serializable;

/**
 * 所有传感器所有时间段数据汇总
 * 
 * @author yinhong
 *
 */
public class AllSensorAllPastInfo implements Serializable {

	private static final long serialVersionUID = 9003146758060907711L;
	private AllSensorPastInfo dayPastSensorInfo;
	private AllSensorPastInfo weekPastSensorInfo;
	private AllSensorPastInfo monthPastSensorInfo;

	public AllSensorPastInfo getDayPastSensorInfo() {
		return dayPastSensorInfo;
	}

	public void setDayPastSensorInfo(AllSensorPastInfo dayPastSensorInfo) {
		this.dayPastSensorInfo = dayPastSensorInfo;
	}

	public AllSensorPastInfo getWeekPastSensorInfo() {
		return weekPastSensorInfo;
	}

	public void setWeekPastSensorInfo(AllSensorPastInfo weekPastSensorInfo) {
		this.weekPastSensorInfo = weekPastSensorInfo;
	}

	public AllSensorPastInfo getMonthPastSensorInfo() {
		return monthPastSensorInfo;
	}

	public void setMonthPastSensorInfo(AllSensorPastInfo monthPastSensorInfo) {
		this.monthPastSensorInfo = monthPastSensorInfo;
	}

}
