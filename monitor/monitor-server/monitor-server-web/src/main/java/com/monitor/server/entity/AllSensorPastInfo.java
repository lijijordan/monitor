package com.monitor.server.entity;

import java.io.Serializable;
import java.util.List;

import com.monitor.common.model.DataPointsStatisticsInfo;

/**
 * 所有传感器一段时间数据汇总
 * 
 * @author yinhong
 *
 */
public class AllSensorPastInfo implements Serializable {

	private static final long serialVersionUID = 1262332799116848133L;

	private List<DataPointsStatisticsInfo> phValueList;
	private List<DataPointsStatisticsInfo> salinityValueList;
	private List<DataPointsStatisticsInfo> tempValueList;
	private List<DataPointsStatisticsInfo> lightValueList;
	private List<DataPointsStatisticsInfo> dtsValueList;

	public List<DataPointsStatisticsInfo> getPhValueList() {
		return phValueList;
	}

	public void setPhValueList(List<DataPointsStatisticsInfo> phValueList) {
		this.phValueList = phValueList;
	}

	public List<DataPointsStatisticsInfo> getSalinityValueList() {
		return salinityValueList;
	}

	public void setSalinityValueList(List<DataPointsStatisticsInfo> salinityValueList) {
		this.salinityValueList = salinityValueList;
	}

	public List<DataPointsStatisticsInfo> getTempValueList() {
		return tempValueList;
	}

	public void setTempValueList(List<DataPointsStatisticsInfo> tempValueList) {
		this.tempValueList = tempValueList;
	}

	public List<DataPointsStatisticsInfo> getLightValueList() {
		return lightValueList;
	}

	public void setLightValueList(List<DataPointsStatisticsInfo> lightValueList) {
		this.lightValueList = lightValueList;
	}

	public List<DataPointsStatisticsInfo> getDtsValueList() {
		return dtsValueList;
	}

	public void setDtsValueList(List<DataPointsStatisticsInfo> dtsValueList) {
		this.dtsValueList = dtsValueList;
	}

}
