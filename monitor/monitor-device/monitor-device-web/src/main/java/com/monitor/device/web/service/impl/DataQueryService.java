package com.monitor.device.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.common.define.DataTypeEnum;
import com.monitor.common.define.QueryScopeEnum;
import com.monitor.common.vo.DataQueryVo;
import com.monitor.device.web.dao.DataPointsActiveInfoMapper;
import com.monitor.device.web.dao.DataPointsStatisticsInfoMapper;
import com.monitor.device.web.dao.TemperatureInfoDao;
import com.monitor.device.web.service.IDataQueryService;

@Transactional
@Service()
public class DataQueryService implements IDataQueryService {

	@Resource
	private TemperatureInfoDao dao;

	@Resource
	private DataPointsActiveInfoMapper dataPointsActiveDao;

	@Resource
	private DataPointsStatisticsInfoMapper dataPointsStatisticsDao;

	public DataQueryService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object queryCurrentData(DataQueryVo vo) {
		return dataPointsActiveDao.selectByDeviceSn(vo);
	}

	@Override
	public List<Object> queryHistoryData(DataQueryVo vo) {
		// TODO Auto-generated method stub
		return null;
	}

}
