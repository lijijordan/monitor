package com.monitor.server.entity.biz;

import java.io.Serializable;

/**
 * 各种传感器值汇总值
 * 
 * @author yinhong
 *
 */
public class AllSensorCurInfo implements Serializable {

  private static final long serialVersionUID = 6834622589607657812L;
  private String ph;
  private String temperature;
  private String light;
  private String salinity;
  private String tds;
  private String pm25;
  private String pm10;
  private String hcho;
  private String tvoc;

  public String getHcho() {
    return hcho;
  }

  public void setHcho(String hcho) {
    this.hcho = hcho;
  }

  public String getTvoc() {
    return tvoc;
  }

  public void setTvoc(String tvoc) {
    this.tvoc = tvoc;
  }

  public String getPm25() {
    return pm25;
  }

  public void setPm25(String pm25) {
    this.pm25 = pm25;
  }

  public String getPm10() {
    return pm10;
  }

  public void setPm10(String pm10) {
    this.pm10 = pm10;
  }

  public String getPh() {
    return ph;
  }

  public void setPh(String ph) {
    this.ph = ph;
  }

  public String getTemperature() {
    return temperature;
  }

  public void setTemperature(String temperature) {
    this.temperature = temperature;
  }

  public String getLight() {
    return light;
  }

  public void setLight(String light) {
    this.light = light;
  }

  public String getSalinity() {
    return salinity;
  }

  public void setSalinity(String salinity) {
    this.salinity = salinity;
  }

  public String getTds() {
    return tds;
  }

  public void setTds(String tds) {
    this.tds = tds;
  }

}
