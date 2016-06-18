package com.monitor.device.web.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.device.web.dao.TemperatureInfoMapper;
import com.monitor.device.web.model.TemperatureInfo;
import com.monitor.device.web.service.ITemperatureService;

@Transactional
@Service
public class TemperatureServiceImpl implements ITemperatureService {

	@Resource
	private TemperatureInfoMapper dao;

	@Override
	public void Add(TemperatureInfo obj) {

		dao.insert(obj);
	}

	@Override
	public TemperatureInfo Get(int id) {
		return dao.selectByPrimaryKey(id);
	}

}
