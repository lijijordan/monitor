package com.monitor.server.entity.biz;

import java.io.Serializable;
import java.util.List;

import com.monitor.server.entity.dev.DataPointsDevStatisticsInfo;

/**
 * 传感器所有时间段数据汇总
 * 
 * @author yinhong
 *
 */
public class SensorAllPastInfo implements Serializable {

	private static final long serialVersionUID = -8371617271403638118L;
	private List<DataPointsDevStatisticsInfo> dayPastSensorInfo;
	private List<DataPointsDevStatisticsInfo> weekPastSensorInfo;
	private List<DataPointsDevStatisticsInfo> monthPastSensorInfo;

	public List<DataPointsDevStatisticsInfo> getDayPastSensorInfo() {
		return dayPastSensorInfo;
	}

	public void setDayPastSensorInfo(List<DataPointsDevStatisticsInfo> dayPastSensorInfo) {
		this.dayPastSensorInfo = dayPastSensorInfo;
	}

	public List<DataPointsDevStatisticsInfo> getWeekPastSensorInfo() {
		return weekPastSensorInfo;
	}

	public void setWeekPastSensorInfo(List<DataPointsDevStatisticsInfo> weekPastSensorInfo) {
		this.weekPastSensorInfo = weekPastSensorInfo;
	}

	public List<DataPointsDevStatisticsInfo> getMonthPastSensorInfo() {
		return monthPastSensorInfo;
	}

	public void setMonthPastSensorInfo(List<DataPointsDevStatisticsInfo> monthPastSensorInfo) {
		this.monthPastSensorInfo = monthPastSensorInfo;
	}

}
