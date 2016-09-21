package com.monitor.device.web.dao;

import com.monitor.device.web.model.DeviceInfo;

public interface DeviceInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DeviceInfo record);

    int insertSelective(DeviceInfo record);

    DeviceInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DeviceInfo record);

    int updateByPrimaryKey(DeviceInfo record);
    
    DeviceInfo selectBySn(String sn);
}