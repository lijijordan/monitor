package com.monitor.device.web.dao;

import com.monitor.device.web.model.DataPointsStatisticsLogInfo;

public interface DataPointsStatisticsLogInfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(DataPointsStatisticsLogInfo record);

	int insertSelective(DataPointsStatisticsLogInfo record);

	DataPointsStatisticsLogInfo selectByPrimaryKey(Long id);

	DataPointsStatisticsLogInfo selectBySensorKey(Long sensorId);

	int updateByPrimaryKeySelective(DataPointsStatisticsLogInfo record);

	int updateByPrimaryKey(DataPointsStatisticsLogInfo record);
}