package com.monitor.server.comm;

import java.io.Serializable;

/**
 * 首页各种数据
 * 
 * @author yinhong
 *
 */
public class HomePageInfo implements Serializable {

	private static final long serialVersionUID = 4048394528299011947L;

	private String phCurValue;
	private String ecCurValue;
	private String tempCurValue;
	private String lightCurValue;

	private String averageHealth;
	private String phHealth;
	private String ecHealth;
	private String tempHealth;
	private String lightHealth;

	private String phAverageValueByDay;
	private String ecAverageValueByDay;
	private String tempAverageValueByDay;
	private String lightAverageValueByDay;

	private String phAverageValueByWeek;
	private String ecAverageValueByWeek;
	private String tempAverageValueByWeek;
	private String lightAverageValueByWeek;

	private String phAverageValueByMonth;
	private String ecAverageValueByMonth;
	private String tempAverageValueByMonth;
	private String lightAverageValueByMonth;

	private String phMaxValueByDay;
	private String ecMaxValueByDay;
	private String tempMaxValueByDay;
	private String lightMaxValueByDay;

	private String phMaxValueByWeek;
	private String ecMaxValueByWeek;
	private String tempMaxValueByWeek;
	private String lightMaxValueByWeek;

	private String phMaxValueByMonth;
	private String ecMaxValueByMonth;
	private String tempMaxValueByMonth;
	private String lightMaxValueByMonth;

	private String phMinValueByDay;
	private String ecMinValueByDay;
	private String tempMinValueByDay;
	private String lightMinValueByDay;

	private String phMinValueByWeek;
	private String ecMinValueByWeek;
	private String tempMinValueByWeek;
	private String lightMinValueByWeek;

	private String phMinValueByMonth;
	private String ecMinValueByMonth;
	private String tempMinValueByMonth;
	private String lightMinValueByMonth;

	public String getAverageHealth() {
		return averageHealth;
	}

	public void setAverageHealth(String averageHealth) {
		this.averageHealth = averageHealth;
	}

	public String getPhCurValue() {
		return phCurValue;
	}

	public void setPhCurValue(String phCurValue) {
		this.phCurValue = phCurValue;
	}

	public String getEcCurValue() {
		return ecCurValue;
	}

	public void setEcCurValue(String ecCurValue) {
		this.ecCurValue = ecCurValue;
	}

	public String getTempCurValue() {
		return tempCurValue;
	}

	public void setTempCurValue(String tempCurValue) {
		this.tempCurValue = tempCurValue;
	}

	public String getLightCurValue() {
		return lightCurValue;
	}

	public void setLightCurValue(String lightCurValue) {
		this.lightCurValue = lightCurValue;
	}

	public String getPhHealth() {
		return phHealth;
	}

	public void setPhHealth(String phHealth) {
		this.phHealth = phHealth;
	}

	public String getEcHealth() {
		return ecHealth;
	}

	public void setEcHealth(String ecHealth) {
		this.ecHealth = ecHealth;
	}

	public String getTempHealth() {
		return tempHealth;
	}

	public void setTempHealth(String tempHealth) {
		this.tempHealth = tempHealth;
	}

	public String getLightHealth() {
		return lightHealth;
	}

	public void setLightHealth(String lightHealth) {
		this.lightHealth = lightHealth;
	}

	public String getPhAverageValueByDay() {
		return phAverageValueByDay;
	}

	public void setPhAverageValueByDay(String phAverageValueByDay) {
		this.phAverageValueByDay = phAverageValueByDay;
	}

	public String getEcAverageValueByDay() {
		return ecAverageValueByDay;
	}

	public void setEcAverageValueByDay(String ecAverageValueByDay) {
		this.ecAverageValueByDay = ecAverageValueByDay;
	}

	public String getTempAverageValueByDay() {
		return tempAverageValueByDay;
	}

	public void setTempAverageValueByDay(String tempAverageValueByDay) {
		this.tempAverageValueByDay = tempAverageValueByDay;
	}

	public String getLightAverageValueByDay() {
		return lightAverageValueByDay;
	}

	public void setLightAverageValueByDay(String lightAverageValueByDay) {
		this.lightAverageValueByDay = lightAverageValueByDay;
	}

	public String getPhAverageValueByWeek() {
		return phAverageValueByWeek;
	}

	public void setPhAverageValueByWeek(String phAverageValueByWeek) {
		this.phAverageValueByWeek = phAverageValueByWeek;
	}

	public String getEcAverageValueByWeek() {
		return ecAverageValueByWeek;
	}

	public void setEcAverageValueByWeek(String ecAverageValueByWeek) {
		this.ecAverageValueByWeek = ecAverageValueByWeek;
	}

	public String getTempAverageValueByWeek() {
		return tempAverageValueByWeek;
	}

	public void setTempAverageValueByWeek(String tempAverageValueByWeek) {
		this.tempAverageValueByWeek = tempAverageValueByWeek;
	}

	public String getLightAverageValueByWeek() {
		return lightAverageValueByWeek;
	}

	public void setLightAverageValueByWeek(String lightAverageValueByWeek) {
		this.lightAverageValueByWeek = lightAverageValueByWeek;
	}

	public String getPhAverageValueByMonth() {
		return phAverageValueByMonth;
	}

	public void setPhAverageValueByMonth(String phAverageValueByMonth) {
		this.phAverageValueByMonth = phAverageValueByMonth;
	}

	public String getEcAverageValueByMonth() {
		return ecAverageValueByMonth;
	}

	public void setEcAverageValueByMonth(String ecAverageValueByMonth) {
		this.ecAverageValueByMonth = ecAverageValueByMonth;
	}

	public String getTempAverageValueByMonth() {
		return tempAverageValueByMonth;
	}

	public void setTempAverageValueByMonth(String tempAverageValueByMonth) {
		this.tempAverageValueByMonth = tempAverageValueByMonth;
	}

	public String getLightAverageValueByMonth() {
		return lightAverageValueByMonth;
	}

	public void setLightAverageValueByMonth(String lightAverageValueByMonth) {
		this.lightAverageValueByMonth = lightAverageValueByMonth;
	}

	public String getPhMaxValueByDay() {
		return phMaxValueByDay;
	}

	public void setPhMaxValueByDay(String phMaxValueByDay) {
		this.phMaxValueByDay = phMaxValueByDay;
	}

	public String getEcMaxValueByDay() {
		return ecMaxValueByDay;
	}

	public void setEcMaxValueByDay(String ecMaxValueByDay) {
		this.ecMaxValueByDay = ecMaxValueByDay;
	}

	public String getTempMaxValueByDay() {
		return tempMaxValueByDay;
	}

	public void setTempMaxValueByDay(String tempMaxValueByDay) {
		this.tempMaxValueByDay = tempMaxValueByDay;
	}

	public String getLightMaxValueByDay() {
		return lightMaxValueByDay;
	}

	public void setLightMaxValueByDay(String lightMaxValueByDay) {
		this.lightMaxValueByDay = lightMaxValueByDay;
	}

	public String getPhMaxValueByWeek() {
		return phMaxValueByWeek;
	}

	public void setPhMaxValueByWeek(String phMaxValueByWeek) {
		this.phMaxValueByWeek = phMaxValueByWeek;
	}

	public String getEcMaxValueByWeek() {
		return ecMaxValueByWeek;
	}

	public void setEcMaxValueByWeek(String ecMaxValueByWeek) {
		this.ecMaxValueByWeek = ecMaxValueByWeek;
	}

	public String getTempMaxValueByWeek() {
		return tempMaxValueByWeek;
	}

	public void setTempMaxValueByWeek(String tempMaxValueByWeek) {
		this.tempMaxValueByWeek = tempMaxValueByWeek;
	}

	public String getLightMaxValueByWeek() {
		return lightMaxValueByWeek;
	}

	public void setLightMaxValueByWeek(String lightMaxValueByWeek) {
		this.lightMaxValueByWeek = lightMaxValueByWeek;
	}

	public String getPhMaxValueByMonth() {
		return phMaxValueByMonth;
	}

	public void setPhMaxValueByMonth(String phMaxValueByMonth) {
		this.phMaxValueByMonth = phMaxValueByMonth;
	}

	public String getEcMaxValueByMonth() {
		return ecMaxValueByMonth;
	}

	public void setEcMaxValueByMonth(String ecMaxValueByMonth) {
		this.ecMaxValueByMonth = ecMaxValueByMonth;
	}

	public String getTempMaxValueByMonth() {
		return tempMaxValueByMonth;
	}

	public void setTempMaxValueByMonth(String tempMaxValueByMonth) {
		this.tempMaxValueByMonth = tempMaxValueByMonth;
	}

	public String getLightMaxValueByMonth() {
		return lightMaxValueByMonth;
	}

	public void setLightMaxValueByMonth(String lightMaxValueByMonth) {
		this.lightMaxValueByMonth = lightMaxValueByMonth;
	}

	public String getPhMinValueByDay() {
		return phMinValueByDay;
	}

	public void setPhMinValueByDay(String phMinValueByDay) {
		this.phMinValueByDay = phMinValueByDay;
	}

	public String getEcMinValueByDay() {
		return ecMinValueByDay;
	}

	public void setEcMinValueByDay(String ecMinValueByDay) {
		this.ecMinValueByDay = ecMinValueByDay;
	}

	public String getTempMinValueByDay() {
		return tempMinValueByDay;
	}

	public void setTempMinValueByDay(String tempMinValueByDay) {
		this.tempMinValueByDay = tempMinValueByDay;
	}

	public String getLightMinValueByDay() {
		return lightMinValueByDay;
	}

	public void setLightMinValueByDay(String lightMinValueByDay) {
		this.lightMinValueByDay = lightMinValueByDay;
	}

	public String getPhMinValueByWeek() {
		return phMinValueByWeek;
	}

	public void setPhMinValueByWeek(String phMinValueByWeek) {
		this.phMinValueByWeek = phMinValueByWeek;
	}

	public String getEcMinValueByWeek() {
		return ecMinValueByWeek;
	}

	public void setEcMinValueByWeek(String ecMinValueByWeek) {
		this.ecMinValueByWeek = ecMinValueByWeek;
	}

	public String getTempMinValueByWeek() {
		return tempMinValueByWeek;
	}

	public void setTempMinValueByWeek(String tempMinValueByWeek) {
		this.tempMinValueByWeek = tempMinValueByWeek;
	}

	public String getLightMinValueByWeek() {
		return lightMinValueByWeek;
	}

	public void setLightMinValueByWeek(String lightMinValueByWeek) {
		this.lightMinValueByWeek = lightMinValueByWeek;
	}

	public String getPhMinValueByMonth() {
		return phMinValueByMonth;
	}

	public void setPhMinValueByMonth(String phMinValueByMonth) {
		this.phMinValueByMonth = phMinValueByMonth;
	}

	public String getEcMinValueByMonth() {
		return ecMinValueByMonth;
	}

	public void setEcMinValueByMonth(String ecMinValueByMonth) {
		this.ecMinValueByMonth = ecMinValueByMonth;
	}

	public String getTempMinValueByMonth() {
		return tempMinValueByMonth;
	}

	public void setTempMinValueByMonth(String tempMinValueByMonth) {
		this.tempMinValueByMonth = tempMinValueByMonth;
	}

	public String getLightMinValueByMonth() {
		return lightMinValueByMonth;
	}

	public void setLightMinValueByMonth(String lightMinValueByMonth) {
		this.lightMinValueByMonth = lightMinValueByMonth;
	}

}
