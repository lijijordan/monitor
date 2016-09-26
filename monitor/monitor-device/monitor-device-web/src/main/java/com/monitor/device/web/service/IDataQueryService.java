package com.monitor.device.web.service;

import java.util.List;

import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.DataQueryVo;

public interface IDataQueryService {
	List<DataPointsStatisticsInfo> queryHistoryData(DataQueryVo vo);

	Object queryCurrentData(DataQueryVo vo);
}
