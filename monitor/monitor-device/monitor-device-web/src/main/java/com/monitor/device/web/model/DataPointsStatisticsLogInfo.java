package com.monitor.device.web.model;

import java.util.Date;

public class DataPointsStatisticsLogInfo {
    private Long id;

    private Long sensorid;

    private Date laststatistics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSensorid() {
        return sensorid;
    }

    public void setSensorid(Long sensorid) {
        this.sensorid = sensorid;
    }

    public Date getLaststatistics() {
        return laststatistics;
    }

    public void setLaststatistics(Date laststatistics) {
        this.laststatistics = laststatistics;
    }
}