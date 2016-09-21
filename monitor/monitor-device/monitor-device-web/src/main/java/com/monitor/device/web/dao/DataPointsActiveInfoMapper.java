package com.monitor.device.web.dao;

import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.vo.DataQueryVo;

public interface DataPointsActiveInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DataPointsActiveInfo record);

    int insertSelective(DataPointsActiveInfo record);

    DataPointsActiveInfo selectByPrimaryKey(Long id);

    DataPointsActiveInfo selectByDeviceSn(DataQueryVo vo);
    
    int updateByPrimaryKeySelective(DataPointsActiveInfo record);

    int updateByPrimaryKey(DataPointsActiveInfo record);
}