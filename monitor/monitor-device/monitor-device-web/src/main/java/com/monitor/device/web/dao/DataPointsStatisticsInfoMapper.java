package com.monitor.device.web.dao;

import com.monitor.common.model.DataPointsStatisticsInfo;

public interface DataPointsStatisticsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataPointsStatisticsInfo record);

    int insertSelective(DataPointsStatisticsInfo record);

    DataPointsStatisticsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataPointsStatisticsInfo record);

    int updateByPrimaryKey(DataPointsStatisticsInfo record);
}