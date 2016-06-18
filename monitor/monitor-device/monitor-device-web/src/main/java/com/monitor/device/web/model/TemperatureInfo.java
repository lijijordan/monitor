package com.monitor.device.web.model;

import java.util.Date;

public class TemperatureInfo {
    private Integer temperatureId;

    private String temperatureValue;

    private Date temperatureTime;

    private Integer userId;

    private Integer fishEquId;

    public Integer getTemperatureId() {
        return temperatureId;
    }

    public void setTemperatureId(Integer temperatureId) {
        this.temperatureId = temperatureId;
    }

    public String getTemperatureValue() {
        return temperatureValue;
    }

    public void setTemperatureValue(String temperatureValue) {
        this.temperatureValue = temperatureValue == null ? null : temperatureValue.trim();
    }

    public Date getTemperatureTime() {
        return temperatureTime;
    }

    public void setTemperatureTime(Date temperatureTime) {
        this.temperatureTime = temperatureTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getFishEquId() {
        return fishEquId;
    }

    public void setFishEquId(Integer fishEquId) {
        this.fishEquId = fishEquId;
    }
}