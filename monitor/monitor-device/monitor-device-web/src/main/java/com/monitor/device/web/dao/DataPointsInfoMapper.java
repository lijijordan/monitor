package com.monitor.device.web.dao;

import com.monitor.common.model.DataPointsInfo;

public interface DataPointsInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataPointsInfo record);

    int insertSelective(DataPointsInfo record);

    DataPointsInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataPointsInfo record);

    int updateByPrimaryKey(DataPointsInfo record);
}