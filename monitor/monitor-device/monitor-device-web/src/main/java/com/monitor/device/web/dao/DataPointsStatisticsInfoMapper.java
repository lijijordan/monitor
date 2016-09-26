package com.monitor.device.web.dao;

import java.util.List;

import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.DataQueryVo;

public interface DataPointsStatisticsInfoMapper {
	int deleteByPrimaryKey(Long id);

	int insert(DataPointsStatisticsInfo record);

	int insertSelective(DataPointsStatisticsInfo record);

	DataPointsStatisticsInfo selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(DataPointsStatisticsInfo record);

	int updateByPrimaryKey(DataPointsStatisticsInfo record);

	List<DataPointsStatisticsInfo> selectByDay(DataQueryVo vo);

	List<DataPointsStatisticsInfo> selectByWeek(DataQueryVo vo);

	List<DataPointsStatisticsInfo> selectByMonth(DataQueryVo vo);
}