package com.monitor.device.web.dao;

import com.monitor.common.model.DataPointsActiveInfo;

public interface DataPointsActiveInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataPointsActiveInfo record);

    int insertSelective(DataPointsActiveInfo record);

    DataPointsActiveInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DataPointsActiveInfo record);

    int updateByPrimaryKey(DataPointsActiveInfo record);
}