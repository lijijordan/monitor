package com.monitor.server.comm;

import java.io.Serializable;
import java.util.List;

import com.monitor.common.model.DataPointsStatisticsInfo;

/**
 * 传感器所有时间段数据汇总
 * 
 * @author yinhong
 *
 */
public class SensorAllPastInfo implements Serializable {

	private static final long serialVersionUID = -8371617271403638118L;
	private List<DataPointsStatisticsInfo> dayPastSensorInfo;
	private List<DataPointsStatisticsInfo> weekPastSensorInfo;
	private List<DataPointsStatisticsInfo> monthPastSensorInfo;

	public List<DataPointsStatisticsInfo> getDayPastSensorInfo() {
		return dayPastSensorInfo;
	}

	public void setDayPastSensorInfo(List<DataPointsStatisticsInfo> dayPastSensorInfo) {
		this.dayPastSensorInfo = dayPastSensorInfo;
	}

	public List<DataPointsStatisticsInfo> getWeekPastSensorInfo() {
		return weekPastSensorInfo;
	}

	public void setWeekPastSensorInfo(List<DataPointsStatisticsInfo> weekPastSensorInfo) {
		this.weekPastSensorInfo = weekPastSensorInfo;
	}

	public List<DataPointsStatisticsInfo> getMonthPastSensorInfo() {
		return monthPastSensorInfo;
	}

	public void setMonthPastSensorInfo(List<DataPointsStatisticsInfo> monthPastSensorInfo) {
		this.monthPastSensorInfo = monthPastSensorInfo;
	}

}
