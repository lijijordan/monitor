package com.monitor.device.web.dao;

import org.springframework.stereotype.Component;

import com.monitor.device.web.model.TemperatureInfo;

@Component
public interface TemperatureInfoDao {
	int insert(TemperatureInfo record);

	TemperatureInfo selectByPrimaryKey(Integer temperatureId);

}