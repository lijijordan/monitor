package com.monitor.device.web.service;

import com.monitor.common.vo.DataPointsVo;
import com.monitor.common.vo.DataQueryVo;

public interface IDataPointsService {
	public void Add(DataPointsVo vo);

	public void Statistics(DataQueryVo vo);
}
