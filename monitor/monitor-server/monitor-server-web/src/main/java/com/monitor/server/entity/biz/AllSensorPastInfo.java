package com.monitor.server.entity.biz;

import java.io.Serializable;
import java.util.List;

import com.monitor.server.entity.dev.DataPointsDevStatisticsInfo;

/**
 * 所有传感器一段时间数据汇总
 * 
 * @author yinhong
 *
 */
public class AllSensorPastInfo implements Serializable {

  private static final long serialVersionUID = 1262332799116848133L;

  private List<DataPointsDevStatisticsInfo> phValueList;
  private List<DataPointsDevStatisticsInfo> salinityValueList;
  private List<DataPointsDevStatisticsInfo> tempValueList;
  private List<DataPointsDevStatisticsInfo> lightValueList;
  private List<DataPointsDevStatisticsInfo> dtsValueList;
  private List<DataPointsDevStatisticsInfo> pm25ValueList;
  private List<DataPointsDevStatisticsInfo> pm10ValueList;

  public List<DataPointsDevStatisticsInfo> getPm25ValueList() {
    return pm25ValueList;
  }

  public void setPm25ValueList(List<DataPointsDevStatisticsInfo> pm25ValueList) {
    this.pm25ValueList = pm25ValueList;
  }

  public List<DataPointsDevStatisticsInfo> getPm10ValueList() {
    return pm10ValueList;
  }

  public void setPm10ValueList(List<DataPointsDevStatisticsInfo> pm10ValueList) {
    this.pm10ValueList = pm10ValueList;
  }

  public List<DataPointsDevStatisticsInfo> getPhValueList() {
    return phValueList;
  }

  public void setPhValueList(List<DataPointsDevStatisticsInfo> phValueList) {
    this.phValueList = phValueList;
  }

  public List<DataPointsDevStatisticsInfo> getSalinityValueList() {
    return salinityValueList;
  }

  public void setSalinityValueList(List<DataPointsDevStatisticsInfo> salinityValueList) {
    this.salinityValueList = salinityValueList;
  }

  public List<DataPointsDevStatisticsInfo> getTempValueList() {
    return tempValueList;
  }

  public void setTempValueList(List<DataPointsDevStatisticsInfo> tempValueList) {
    this.tempValueList = tempValueList;
  }

  public List<DataPointsDevStatisticsInfo> getLightValueList() {
    return lightValueList;
  }

  public void setLightValueList(List<DataPointsDevStatisticsInfo> lightValueList) {
    this.lightValueList = lightValueList;
  }

  public List<DataPointsDevStatisticsInfo> getDtsValueList() {
    return dtsValueList;
  }

  public void setDtsValueList(List<DataPointsDevStatisticsInfo> dtsValueList) {
    this.dtsValueList = dtsValueList;
  }

}
