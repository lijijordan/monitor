package com.monitor.server.comm;

import java.io.Serializable;

import com.monitor.common.model.DataPointsStatisticsInfo;

/**
 * 一段时间各种传感器数据汇总
 * 
 * @author yinhong
 *
 */
public class PastSensorInfo implements Serializable {

	private static final long serialVersionUID = 1262332799116848133L;

	private DataPointsStatisticsInfo phValue;
	private DataPointsStatisticsInfo salinityValue;
	private DataPointsStatisticsInfo tempValue;
	private DataPointsStatisticsInfo lightValue;
	private DataPointsStatisticsInfo dtsValue;

	public DataPointsStatisticsInfo getDtsValue() {
		return dtsValue;
	}

	public void setDtsValue(DataPointsStatisticsInfo dtsValue) {
		this.dtsValue = dtsValue;
	}

	public DataPointsStatisticsInfo getPhValue() {
		return phValue;
	}

	public void setPhValue(DataPointsStatisticsInfo phValue) {
		this.phValue = phValue;
	}

	public DataPointsStatisticsInfo getSalinityValue() {
		return salinityValue;
	}

	public void setSalinityValue(DataPointsStatisticsInfo salinityValue) {
		this.salinityValue = salinityValue;
	}

	public DataPointsStatisticsInfo getTempValue() {
		return tempValue;
	}

	public void setTempValue(DataPointsStatisticsInfo tempValue) {
		this.tempValue = tempValue;
	}

	public DataPointsStatisticsInfo getLightValue() {
		return lightValue;
	}

	public void setLightValue(DataPointsStatisticsInfo lightValue) {
		this.lightValue = lightValue;
	}

}
