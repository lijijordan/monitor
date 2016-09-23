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
	private String salinityCurValue;
	private String tdsCurValue;
	private String tempCurValue;
	private String lightCurValue;

	private String averageHealth;
	private String phHealth;
	private String salinityHealth;
	private String tdsHealth;
	private String tempHealth;
	private String lightHealth;

	private String phAverageValueByDay;
	private String salinityAverageValueByDay;
	private String tdsAverageValueByDay;
	private String tempAverageValueByDay;
	private String lightAverageValueByDay;

	private String phAverageValueByWeek;
	private String salinityAverageValueByWeek;
	private String tdsAverageValueByWeek;
	private String tempAverageValueByWeek;
	private String lightAverageValueByWeek;

	private String phAverageValueByMonth;
	private String salinityAverageValueByMonth;
	private String tdsAverageValueByMonth;
	private String tempAverageValueByMonth;
	private String lightAverageValueByMonth;

	private String phMaxValueByDay;
	private String salinityMaxValueByDay;
	private String tdsMaxValueByDay;
	private String tempMaxValueByDay;
	private String lightMaxValueByDay;

	private String phMaxValueByWeek;
	private String salinityMaxValueByWeek;
	private String tdsMaxValueByWeek;
	private String tempMaxValueByWeek;
	private String lightMaxValueByWeek;

	private String phMaxValueByMonth;
	private String salinityMaxValueByMonth;
	private String tdsMaxValueByMonth;
	private String tempMaxValueByMonth;
	private String lightMaxValueByMonth;

	private String phMinValueByDay;
	private String salinityMinValueByDay;
	private String tdsMinValueByDay;
	private String tempMinValueByDay;
	private String lightMinValueByDay;

	private String phMinValueByWeek;
	private String salinityMinValueByWeek;
	private String tdsMinValueByWeek;
	private String tempMinValueByWeek;
	private String lightMinValueByWeek;

	private String phMinValueByMonth;
	private String salinityMinValueByMonth;
	private String tdsMinValueByMonth;
	private String tempMinValueByMonth;
	private String lightMinValueByMonth;

	public String getPhCurValue() {
		return phCurValue;
	}

	public void setPhCurValue(String phCurValue) {
		this.phCurValue = phCurValue;
	}

	public String getSalinityCurValue() {
		return salinityCurValue;
	}

	public void setSalinityCurValue(String salinityCurValue) {
		this.salinityCurValue = salinityCurValue;
	}

	public String getTdsCurValue() {
		return tdsCurValue;
	}

	public void setTdsCurValue(String tdsCurValue) {
		this.tdsCurValue = tdsCurValue;
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

	public String getAverageHealth() {
		return averageHealth;
	}

	public void setAverageHealth(String averageHealth) {
		this.averageHealth = averageHealth;
	}

	public String getPhHealth() {
		return phHealth;
	}

	public void setPhHealth(String phHealth) {
		this.phHealth = phHealth;
	}

	public String getSalinityHealth() {
		return salinityHealth;
	}

	public void setSalinityHealth(String salinityHealth) {
		this.salinityHealth = salinityHealth;
	}

	public String getTdsHealth() {
		return tdsHealth;
	}

	public void setTdsHealth(String tdsHealth) {
		this.tdsHealth = tdsHealth;
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

	public String getSalinityAverageValueByDay() {
		return salinityAverageValueByDay;
	}

	public void setSalinityAverageValueByDay(String salinityAverageValueByDay) {
		this.salinityAverageValueByDay = salinityAverageValueByDay;
	}

	public String getTdsAverageValueByDay() {
		return tdsAverageValueByDay;
	}

	public void setTdsAverageValueByDay(String tdsAverageValueByDay) {
		this.tdsAverageValueByDay = tdsAverageValueByDay;
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

	public String getSalinityAverageValueByWeek() {
		return salinityAverageValueByWeek;
	}

	public void setSalinityAverageValueByWeek(String salinityAverageValueByWeek) {
		this.salinityAverageValueByWeek = salinityAverageValueByWeek;
	}

	public String getTdsAverageValueByWeek() {
		return tdsAverageValueByWeek;
	}

	public void setTdsAverageValueByWeek(String tdsAverageValueByWeek) {
		this.tdsAverageValueByWeek = tdsAverageValueByWeek;
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

	public String getSalinityAverageValueByMonth() {
		return salinityAverageValueByMonth;
	}

	public void setSalinityAverageValueByMonth(String salinityAverageValueByMonth) {
		this.salinityAverageValueByMonth = salinityAverageValueByMonth;
	}

	public String getTdsAverageValueByMonth() {
		return tdsAverageValueByMonth;
	}

	public void setTdsAverageValueByMonth(String tdsAverageValueByMonth) {
		this.tdsAverageValueByMonth = tdsAverageValueByMonth;
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

	public String getSalinityMaxValueByDay() {
		return salinityMaxValueByDay;
	}

	public void setSalinityMaxValueByDay(String salinityMaxValueByDay) {
		this.salinityMaxValueByDay = salinityMaxValueByDay;
	}

	public String getTdsMaxValueByDay() {
		return tdsMaxValueByDay;
	}

	public void setTdsMaxValueByDay(String tdsMaxValueByDay) {
		this.tdsMaxValueByDay = tdsMaxValueByDay;
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

	public String getSalinityMaxValueByWeek() {
		return salinityMaxValueByWeek;
	}

	public void setSalinityMaxValueByWeek(String salinityMaxValueByWeek) {
		this.salinityMaxValueByWeek = salinityMaxValueByWeek;
	}

	public String getTdsMaxValueByWeek() {
		return tdsMaxValueByWeek;
	}

	public void setTdsMaxValueByWeek(String tdsMaxValueByWeek) {
		this.tdsMaxValueByWeek = tdsMaxValueByWeek;
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

	public String getSalinityMaxValueByMonth() {
		return salinityMaxValueByMonth;
	}

	public void setSalinityMaxValueByMonth(String salinityMaxValueByMonth) {
		this.salinityMaxValueByMonth = salinityMaxValueByMonth;
	}

	public String getTdsMaxValueByMonth() {
		return tdsMaxValueByMonth;
	}

	public void setTdsMaxValueByMonth(String tdsMaxValueByMonth) {
		this.tdsMaxValueByMonth = tdsMaxValueByMonth;
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

	public String getSalinityMinValueByDay() {
		return salinityMinValueByDay;
	}

	public void setSalinityMinValueByDay(String salinityMinValueByDay) {
		this.salinityMinValueByDay = salinityMinValueByDay;
	}

	public String getTdsMinValueByDay() {
		return tdsMinValueByDay;
	}

	public void setTdsMinValueByDay(String tdsMinValueByDay) {
		this.tdsMinValueByDay = tdsMinValueByDay;
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

	public String getSalinityMinValueByWeek() {
		return salinityMinValueByWeek;
	}

	public void setSalinityMinValueByWeek(String salinityMinValueByWeek) {
		this.salinityMinValueByWeek = salinityMinValueByWeek;
	}

	public String getTdsMinValueByWeek() {
		return tdsMinValueByWeek;
	}

	public void setTdsMinValueByWeek(String tdsMinValueByWeek) {
		this.tdsMinValueByWeek = tdsMinValueByWeek;
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

	public String getSalinityMinValueByMonth() {
		return salinityMinValueByMonth;
	}

	public void setSalinityMinValueByMonth(String salinityMinValueByMonth) {
		this.salinityMinValueByMonth = salinityMinValueByMonth;
	}

	public String getTdsMinValueByMonth() {
		return tdsMinValueByMonth;
	}

	public void setTdsMinValueByMonth(String tdsMinValueByMonth) {
		this.tdsMinValueByMonth = tdsMinValueByMonth;
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
