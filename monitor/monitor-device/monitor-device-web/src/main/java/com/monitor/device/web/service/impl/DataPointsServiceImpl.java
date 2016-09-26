package com.monitor.device.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsInfo;
import com.monitor.common.vo.DataPointsVo;
import com.monitor.common.vo.DataQueryVo;
import com.monitor.device.web.dao.DataPointsActiveInfoMapper;
import com.monitor.device.web.dao.DataPointsInfoMapper;
import com.monitor.device.web.dao.SensorInfoMapper;
import com.monitor.device.web.model.SensorInfo;
import com.monitor.device.web.service.IDataPointsService;

@Transactional
@Service()
public class DataPointsServiceImpl implements IDataPointsService {

	@Resource
	private DataPointsInfoMapper dataPointsDao;

	@Resource
	private DataPointsActiveInfoMapper dataPointsActiveDao;

	@Resource
	private SensorInfoMapper sensorDao;

	public void Add(DataPointsVo vo) {
		SensorInfo sensor = sensorDao.selectByDeviceSn(vo);
		DataPointsInfo record = new DataPointsInfo();
		record.setSensorid(sensor.getId());
		record.setCollecttime(vo.getCollectTime());
		record.setValue(vo.getValue());

		dataPointsDao.insert(record);
		DataQueryVo queryVo = new DataQueryVo();
		queryVo.setDataType(vo.getDataType());
		queryVo.setDeviceSn(vo.getDeviceSn());
		DataPointsActiveInfo activeRecord = dataPointsActiveDao
				.selectByDeviceSn(queryVo);
		if (null == activeRecord) {
			activeRecord = new DataPointsActiveInfo();
			activeRecord.setSensorid(sensor.getId());
			activeRecord.setCollecttime(vo.getCollectTime());
			activeRecord.setValue(vo.getValue());
			dataPointsActiveDao.insert(activeRecord);
		} else {
			activeRecord.setCollecttime(vo.getCollectTime());
			activeRecord.setValue(vo.getValue());
			dataPointsActiveDao.updateByPrimaryKey(activeRecord);
		}
	}

	@Override
	public void Statistics(DataQueryVo vo) {
		// TODO 根据不同周期，生成统计数据

	}
}
