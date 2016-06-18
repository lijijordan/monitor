package com.monitor.device.web.dao;

import com.monitor.device.web.model.TemperatureInfo;

public interface TemperatureInfoMapper {
	int deleteByPrimaryKey(Integer temperatureId);

	int insert(TemperatureInfo record);

	int insertSelective(TemperatureInfo record);

	TemperatureInfo selectByPrimaryKey(Integer temperatureId);

	int updateByPrimaryKeySelective(TemperatureInfo record);

	int updateByPrimaryKey(TemperatureInfo record);
}