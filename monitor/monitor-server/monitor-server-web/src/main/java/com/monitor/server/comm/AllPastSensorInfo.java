package com.monitor.server.comm;

import java.io.Serializable;

/**
 * 一段时间各种传感器数据汇总
 * 
 * @author yinhong
 *
 */
public class AllPastSensorInfo implements Serializable {

	private static final long serialVersionUID = 9003146758060907711L;
	private PastSensorInfo dayPastSensorInfo;
	private PastSensorInfo weekPastSensorInfo;
	private PastSensorInfo monthPastSensorInfo;

	public PastSensorInfo getDayPastSensorInfo() {
		return dayPastSensorInfo;
	}

	public void setDayPastSensorInfo(PastSensorInfo dayPastSensorInfo) {
		this.dayPastSensorInfo = dayPastSensorInfo;
	}

	public PastSensorInfo getWeekPastSensorInfo() {
		return weekPastSensorInfo;
	}

	public void setWeekPastSensorInfo(PastSensorInfo weekPastSensorInfo) {
		this.weekPastSensorInfo = weekPastSensorInfo;
	}

	public PastSensorInfo getMonthPastSensorInfo() {
		return monthPastSensorInfo;
	}

	public void setMonthPastSensorInfo(PastSensorInfo monthPastSensorInfo) {
		this.monthPastSensorInfo = monthPastSensorInfo;
	}

}
