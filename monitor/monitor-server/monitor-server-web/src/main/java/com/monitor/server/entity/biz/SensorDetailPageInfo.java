package com.monitor.server.entity.biz;

import java.io.Serializable;
import java.util.List;

import com.monitor.server.entity.dev.DataPointInfo;

/**
 * 传感器详情页各种数据
 * 
 * @author yinhong
 *
 */
public class SensorDetailPageInfo implements Serializable {

	private static final long serialVersionUID = 4270653431189096031L;

	private String curValue;

	private List<DataPointInfo> averageValueByDay;
	private List<DataPointInfo> averageValueByWeek;
	private List<DataPointInfo> averageValueByMonth;

	private List<DataPointInfo> minValueByDay;
	private List<DataPointInfo> minValueByWeek;
	private List<DataPointInfo> minValueByMonth;

	private List<DataPointInfo> maxValueByDay;
	private List<DataPointInfo> maxValueByWeek;
	private List<DataPointInfo> maxValueByMonth;

	public String getCurValue() {
		return curValue;
	}

	public void setCurValue(String curValue) {
		this.curValue = curValue;
	}

	public List<DataPointInfo> getAverageValueByDay() {
		return averageValueByDay;
	}

	public void setAverageValueByDay(List<DataPointInfo> averageValueByDay) {
		this.averageValueByDay = averageValueByDay;
	}

	public List<DataPointInfo> getAverageValueByWeek() {
		return averageValueByWeek;
	}

	public void setAverageValueByWeek(List<DataPointInfo> averageValueByWeek) {
		this.averageValueByWeek = averageValueByWeek;
	}

	public List<DataPointInfo> getAverageValueByMonth() {
		return averageValueByMonth;
	}

	public void setAverageValueByMonth(List<DataPointInfo> averageValueByMonth) {
		this.averageValueByMonth = averageValueByMonth;
	}

	public List<DataPointInfo> getMinValueByDay() {
		return minValueByDay;
	}

	public void setMinValueByDay(List<DataPointInfo> minValueByDay) {
		this.minValueByDay = minValueByDay;
	}

	public List<DataPointInfo> getMinValueByWeek() {
		return minValueByWeek;
	}

	public void setMinValueByWeek(List<DataPointInfo> minValueByWeek) {
		this.minValueByWeek = minValueByWeek;
	}

	public List<DataPointInfo> getMinValueByMonth() {
		return minValueByMonth;
	}

	public void setMinValueByMonth(List<DataPointInfo> minValueByMonth) {
		this.minValueByMonth = minValueByMonth;
	}

	public List<DataPointInfo> getMaxValueByDay() {
		return maxValueByDay;
	}

	public void setMaxValueByDay(List<DataPointInfo> maxValueByDay) {
		this.maxValueByDay = maxValueByDay;
	}

	public List<DataPointInfo> getMaxValueByWeek() {
		return maxValueByWeek;
	}

	public void setMaxValueByWeek(List<DataPointInfo> maxValueByWeek) {
		this.maxValueByWeek = maxValueByWeek;
	}

	public List<DataPointInfo> getMaxValueByMonth() {
		return maxValueByMonth;
	}

	public void setMaxValueByMonth(List<DataPointInfo> maxValueByMonth) {
		this.maxValueByMonth = maxValueByMonth;
	}

}
