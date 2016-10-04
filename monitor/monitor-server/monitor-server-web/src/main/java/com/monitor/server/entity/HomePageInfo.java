package com.monitor.server.entity;

import java.io.Serializable;
import java.util.List;

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

	private List<DataPointInfo> phAverageValueByDay;
	private List<DataPointInfo> salinityAverageValueByDay;
	private List<DataPointInfo> tdsAverageValueByDay;
	private List<DataPointInfo> tempAverageValueByDay;
	private List<DataPointInfo> lightAverageValueByDay;

	private List<DataPointInfo> phAverageValueByWeek;
	private List<DataPointInfo> salinityAverageValueByWeek;
	private List<DataPointInfo> tdsAverageValueByWeek;
	private List<DataPointInfo> tempAverageValueByWeek;
	private List<DataPointInfo> lightAverageValueByWeek;

	private List<DataPointInfo> phAverageValueByMonth;
	private List<DataPointInfo> salinityAverageValueByMonth;
	private List<DataPointInfo> tdsAverageValueByMonth;
	private List<DataPointInfo> tempAverageValueByMonth;
	private List<DataPointInfo> lightAverageValueByMonth;

	private List<DataPointInfo> phMaxValueByDay;
	private List<DataPointInfo> salinityMaxValueByDay;
	private List<DataPointInfo> tdsMaxValueByDay;
	private List<DataPointInfo> tempMaxValueByDay;
	private List<DataPointInfo> lightMaxValueByDay;

	private List<DataPointInfo> phMaxValueByWeek;
	private List<DataPointInfo> salinityMaxValueByWeek;
	private List<DataPointInfo> tdsMaxValueByWeek;
	private List<DataPointInfo> tempMaxValueByWeek;
	private List<DataPointInfo> lightMaxValueByWeek;

	private List<DataPointInfo> phMaxValueByMonth;
	private List<DataPointInfo> salinityMaxValueByMonth;
	private List<DataPointInfo> tdsMaxValueByMonth;
	private List<DataPointInfo> tempMaxValueByMonth;
	private List<DataPointInfo> lightMaxValueByMonth;

	private List<DataPointInfo> phMinValueByDay;
	private List<DataPointInfo> salinityMinValueByDay;
	private List<DataPointInfo> tdsMinValueByDay;
	private List<DataPointInfo> tempMinValueByDay;
	private List<DataPointInfo> lightMinValueByDay;

	private List<DataPointInfo> phMinValueByWeek;
	private List<DataPointInfo> salinityMinValueByWeek;
	private List<DataPointInfo> tdsMinValueByWeek;
	private List<DataPointInfo> tempMinValueByWeek;
	private List<DataPointInfo> lightMinValueByWeek;

	private List<DataPointInfo> phMinValueByMonth;
	private List<DataPointInfo> salinityMinValueByMonth;
	private List<DataPointInfo> tdsMinValueByMonth;
	private List<DataPointInfo> tempMinValueByMonth;
	private List<DataPointInfo> lightMinValueByMonth;

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

	public List<DataPointInfo> getPhAverageValueByDay() {
		return phAverageValueByDay;
	}

	public void setPhAverageValueByDay(List<DataPointInfo> phAverageValueByDay) {
		this.phAverageValueByDay = phAverageValueByDay;
	}

	public List<DataPointInfo> getSalinityAverageValueByDay() {
		return salinityAverageValueByDay;
	}

	public void setSalinityAverageValueByDay(List<DataPointInfo> salinityAverageValueByDay) {
		this.salinityAverageValueByDay = salinityAverageValueByDay;
	}

	public List<DataPointInfo> getTdsAverageValueByDay() {
		return tdsAverageValueByDay;
	}

	public void setTdsAverageValueByDay(List<DataPointInfo> tdsAverageValueByDay) {
		this.tdsAverageValueByDay = tdsAverageValueByDay;
	}

	public List<DataPointInfo> getTempAverageValueByDay() {
		return tempAverageValueByDay;
	}

	public void setTempAverageValueByDay(List<DataPointInfo> tempAverageValueByDay) {
		this.tempAverageValueByDay = tempAverageValueByDay;
	}

	public List<DataPointInfo> getLightAverageValueByDay() {
		return lightAverageValueByDay;
	}

	public void setLightAverageValueByDay(List<DataPointInfo> lightAverageValueByDay) {
		this.lightAverageValueByDay = lightAverageValueByDay;
	}

	public List<DataPointInfo> getPhAverageValueByWeek() {
		return phAverageValueByWeek;
	}

	public void setPhAverageValueByWeek(List<DataPointInfo> phAverageValueByWeek) {
		this.phAverageValueByWeek = phAverageValueByWeek;
	}

	public List<DataPointInfo> getSalinityAverageValueByWeek() {
		return salinityAverageValueByWeek;
	}

	public void setSalinityAverageValueByWeek(List<DataPointInfo> salinityAverageValueByWeek) {
		this.salinityAverageValueByWeek = salinityAverageValueByWeek;
	}

	public List<DataPointInfo> getTdsAverageValueByWeek() {
		return tdsAverageValueByWeek;
	}

	public void setTdsAverageValueByWeek(List<DataPointInfo> tdsAverageValueByWeek) {
		this.tdsAverageValueByWeek = tdsAverageValueByWeek;
	}

	public List<DataPointInfo> getTempAverageValueByWeek() {
		return tempAverageValueByWeek;
	}

	public void setTempAverageValueByWeek(List<DataPointInfo> tempAverageValueByWeek) {
		this.tempAverageValueByWeek = tempAverageValueByWeek;
	}

	public List<DataPointInfo> getLightAverageValueByWeek() {
		return lightAverageValueByWeek;
	}

	public void setLightAverageValueByWeek(List<DataPointInfo> lightAverageValueByWeek) {
		this.lightAverageValueByWeek = lightAverageValueByWeek;
	}

	public List<DataPointInfo> getPhAverageValueByMonth() {
		return phAverageValueByMonth;
	}

	public void setPhAverageValueByMonth(List<DataPointInfo> phAverageValueByMonth) {
		this.phAverageValueByMonth = phAverageValueByMonth;
	}

	public List<DataPointInfo> getSalinityAverageValueByMonth() {
		return salinityAverageValueByMonth;
	}

	public void setSalinityAverageValueByMonth(List<DataPointInfo> salinityAverageValueByMonth) {
		this.salinityAverageValueByMonth = salinityAverageValueByMonth;
	}

	public List<DataPointInfo> getTdsAverageValueByMonth() {
		return tdsAverageValueByMonth;
	}

	public void setTdsAverageValueByMonth(List<DataPointInfo> tdsAverageValueByMonth) {
		this.tdsAverageValueByMonth = tdsAverageValueByMonth;
	}

	public List<DataPointInfo> getTempAverageValueByMonth() {
		return tempAverageValueByMonth;
	}

	public void setTempAverageValueByMonth(List<DataPointInfo> tempAverageValueByMonth) {
		this.tempAverageValueByMonth = tempAverageValueByMonth;
	}

	public List<DataPointInfo> getLightAverageValueByMonth() {
		return lightAverageValueByMonth;
	}

	public void setLightAverageValueByMonth(List<DataPointInfo> lightAverageValueByMonth) {
		this.lightAverageValueByMonth = lightAverageValueByMonth;
	}

	public List<DataPointInfo> getPhMaxValueByDay() {
		return phMaxValueByDay;
	}

	public void setPhMaxValueByDay(List<DataPointInfo> phMaxValueByDay) {
		this.phMaxValueByDay = phMaxValueByDay;
	}

	public List<DataPointInfo> getSalinityMaxValueByDay() {
		return salinityMaxValueByDay;
	}

	public void setSalinityMaxValueByDay(List<DataPointInfo> salinityMaxValueByDay) {
		this.salinityMaxValueByDay = salinityMaxValueByDay;
	}

	public List<DataPointInfo> getTdsMaxValueByDay() {
		return tdsMaxValueByDay;
	}

	public void setTdsMaxValueByDay(List<DataPointInfo> tdsMaxValueByDay) {
		this.tdsMaxValueByDay = tdsMaxValueByDay;
	}

	public List<DataPointInfo> getTempMaxValueByDay() {
		return tempMaxValueByDay;
	}

	public void setTempMaxValueByDay(List<DataPointInfo> tempMaxValueByDay) {
		this.tempMaxValueByDay = tempMaxValueByDay;
	}

	public List<DataPointInfo> getLightMaxValueByDay() {
		return lightMaxValueByDay;
	}

	public void setLightMaxValueByDay(List<DataPointInfo> lightMaxValueByDay) {
		this.lightMaxValueByDay = lightMaxValueByDay;
	}

	public List<DataPointInfo> getPhMaxValueByWeek() {
		return phMaxValueByWeek;
	}

	public void setPhMaxValueByWeek(List<DataPointInfo> phMaxValueByWeek) {
		this.phMaxValueByWeek = phMaxValueByWeek;
	}

	public List<DataPointInfo> getSalinityMaxValueByWeek() {
		return salinityMaxValueByWeek;
	}

	public void setSalinityMaxValueByWeek(List<DataPointInfo> salinityMaxValueByWeek) {
		this.salinityMaxValueByWeek = salinityMaxValueByWeek;
	}

	public List<DataPointInfo> getTdsMaxValueByWeek() {
		return tdsMaxValueByWeek;
	}

	public void setTdsMaxValueByWeek(List<DataPointInfo> tdsMaxValueByWeek) {
		this.tdsMaxValueByWeek = tdsMaxValueByWeek;
	}

	public List<DataPointInfo> getTempMaxValueByWeek() {
		return tempMaxValueByWeek;
	}

	public void setTempMaxValueByWeek(List<DataPointInfo> tempMaxValueByWeek) {
		this.tempMaxValueByWeek = tempMaxValueByWeek;
	}

	public List<DataPointInfo> getLightMaxValueByWeek() {
		return lightMaxValueByWeek;
	}

	public void setLightMaxValueByWeek(List<DataPointInfo> lightMaxValueByWeek) {
		this.lightMaxValueByWeek = lightMaxValueByWeek;
	}

	public List<DataPointInfo> getPhMaxValueByMonth() {
		return phMaxValueByMonth;
	}

	public void setPhMaxValueByMonth(List<DataPointInfo> phMaxValueByMonth) {
		this.phMaxValueByMonth = phMaxValueByMonth;
	}

	public List<DataPointInfo> getSalinityMaxValueByMonth() {
		return salinityMaxValueByMonth;
	}

	public void setSalinityMaxValueByMonth(List<DataPointInfo> salinityMaxValueByMonth) {
		this.salinityMaxValueByMonth = salinityMaxValueByMonth;
	}

	public List<DataPointInfo> getTdsMaxValueByMonth() {
		return tdsMaxValueByMonth;
	}

	public void setTdsMaxValueByMonth(List<DataPointInfo> tdsMaxValueByMonth) {
		this.tdsMaxValueByMonth = tdsMaxValueByMonth;
	}

	public List<DataPointInfo> getTempMaxValueByMonth() {
		return tempMaxValueByMonth;
	}

	public void setTempMaxValueByMonth(List<DataPointInfo> tempMaxValueByMonth) {
		this.tempMaxValueByMonth = tempMaxValueByMonth;
	}

	public List<DataPointInfo> getLightMaxValueByMonth() {
		return lightMaxValueByMonth;
	}

	public void setLightMaxValueByMonth(List<DataPointInfo> lightMaxValueByMonth) {
		this.lightMaxValueByMonth = lightMaxValueByMonth;
	}

	public List<DataPointInfo> getPhMinValueByDay() {
		return phMinValueByDay;
	}

	public void setPhMinValueByDay(List<DataPointInfo> phMinValueByDay) {
		this.phMinValueByDay = phMinValueByDay;
	}

	public List<DataPointInfo> getSalinityMinValueByDay() {
		return salinityMinValueByDay;
	}

	public void setSalinityMinValueByDay(List<DataPointInfo> salinityMinValueByDay) {
		this.salinityMinValueByDay = salinityMinValueByDay;
	}

	public List<DataPointInfo> getTdsMinValueByDay() {
		return tdsMinValueByDay;
	}

	public void setTdsMinValueByDay(List<DataPointInfo> tdsMinValueByDay) {
		this.tdsMinValueByDay = tdsMinValueByDay;
	}

	public List<DataPointInfo> getTempMinValueByDay() {
		return tempMinValueByDay;
	}

	public void setTempMinValueByDay(List<DataPointInfo> tempMinValueByDay) {
		this.tempMinValueByDay = tempMinValueByDay;
	}

	public List<DataPointInfo> getLightMinValueByDay() {
		return lightMinValueByDay;
	}

	public void setLightMinValueByDay(List<DataPointInfo> lightMinValueByDay) {
		this.lightMinValueByDay = lightMinValueByDay;
	}

	public List<DataPointInfo> getPhMinValueByWeek() {
		return phMinValueByWeek;
	}

	public void setPhMinValueByWeek(List<DataPointInfo> phMinValueByWeek) {
		this.phMinValueByWeek = phMinValueByWeek;
	}

	public List<DataPointInfo> getSalinityMinValueByWeek() {
		return salinityMinValueByWeek;
	}

	public void setSalinityMinValueByWeek(List<DataPointInfo> salinityMinValueByWeek) {
		this.salinityMinValueByWeek = salinityMinValueByWeek;
	}

	public List<DataPointInfo> getTdsMinValueByWeek() {
		return tdsMinValueByWeek;
	}

	public void setTdsMinValueByWeek(List<DataPointInfo> tdsMinValueByWeek) {
		this.tdsMinValueByWeek = tdsMinValueByWeek;
	}

	public List<DataPointInfo> getTempMinValueByWeek() {
		return tempMinValueByWeek;
	}

	public void setTempMinValueByWeek(List<DataPointInfo> tempMinValueByWeek) {
		this.tempMinValueByWeek = tempMinValueByWeek;
	}

	public List<DataPointInfo> getLightMinValueByWeek() {
		return lightMinValueByWeek;
	}

	public void setLightMinValueByWeek(List<DataPointInfo> lightMinValueByWeek) {
		this.lightMinValueByWeek = lightMinValueByWeek;
	}

	public List<DataPointInfo> getPhMinValueByMonth() {
		return phMinValueByMonth;
	}

	public void setPhMinValueByMonth(List<DataPointInfo> phMinValueByMonth) {
		this.phMinValueByMonth = phMinValueByMonth;
	}

	public List<DataPointInfo> getSalinityMinValueByMonth() {
		return salinityMinValueByMonth;
	}

	public void setSalinityMinValueByMonth(List<DataPointInfo> salinityMinValueByMonth) {
		this.salinityMinValueByMonth = salinityMinValueByMonth;
	}

	public List<DataPointInfo> getTdsMinValueByMonth() {
		return tdsMinValueByMonth;
	}

	public void setTdsMinValueByMonth(List<DataPointInfo> tdsMinValueByMonth) {
		this.tdsMinValueByMonth = tdsMinValueByMonth;
	}

	public List<DataPointInfo> getTempMinValueByMonth() {
		return tempMinValueByMonth;
	}

	public void setTempMinValueByMonth(List<DataPointInfo> tempMinValueByMonth) {
		this.tempMinValueByMonth = tempMinValueByMonth;
	}

	public List<DataPointInfo> getLightMinValueByMonth() {
		return lightMinValueByMonth;
	}

	public void setLightMinValueByMonth(List<DataPointInfo> lightMinValueByMonth) {
		this.lightMinValueByMonth = lightMinValueByMonth;
	}

}
