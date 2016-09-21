package com.monitor.device.web.dao;

import com.monitor.common.vo.DataPointsVo;
import com.monitor.common.vo.DataQueryVo;
import com.monitor.device.web.model.SensorInfo;

public interface SensorInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SensorInfo record);

    int insertSelective(SensorInfo record);

    SensorInfo selectByPrimaryKey(Long id);
    
    SensorInfo selectByDeviceSn(DataPointsVo vo);

    int updateByPrimaryKeySelective(SensorInfo record);

    int updateByPrimaryKey(SensorInfo record);
}