package com.monitor.device.web.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.common.define.QueryScopeEnum;
import com.monitor.common.model.DataPointsStatisticsInfo;
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
	public List<DataPointsStatisticsInfo> queryHistoryData(DataQueryVo vo) {
		Date dtNow = new Date();
		List<DataPointsStatisticsInfo> statisticsList = null;
		switch (QueryScopeEnum.fromString(vo.getQueryScopeText())) {
		case Day:
			if (null == vo.getStartTime()) {
				vo.setStartTime(DateUtils.addHours(
						(DateUtils.addDays(dtNow, -1)), 1));
			}
			if (null == vo.getEndTime()) {
				vo.setEndTime(dtNow);
			}
			statisticsList = dataPointsStatisticsDao.selectByDay(vo);
			break;
		case Week:
			if (null == vo.getStartTime()) {
				vo.setStartTime(DateUtils.addWeeks(dtNow, -1));
			}
			if (null == vo.getEndTime()) {
				vo.setEndTime(dtNow);
			}
			statisticsList = dataPointsStatisticsDao.selectByWeek(vo);
			break;
		case Month:
		default:
			if (null == vo.getStartTime()) {
				vo.setStartTime(DateUtils.addMonths(dtNow, -1));
			}
			if (null == vo.getEndTime()) {
				vo.setEndTime(dtNow);
			}
			statisticsList = dataPointsStatisticsDao.selectByMonth(vo);
			break;
		}
		return statisticsList;
	}
}
