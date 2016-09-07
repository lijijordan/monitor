package com.monitor.device.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.device.web.dao.TemperatureInfoDao;
import com.monitor.device.web.define.DataTypeEnum;
import com.monitor.device.web.define.QueryScopeEnum;
import com.monitor.device.web.service.IDataQueryService;

@Transactional
@Service()
public class DataQueryService implements IDataQueryService {

	@Resource
	private TemperatureInfoDao dao;

	public DataQueryService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object queryCurrentData(DataTypeEnum type) {
		switch (type) {
		case Temperature:
			return dao.selectCurrent();
		case Conductivity:
			break;
		case PH:
			break;
		case WaterLine:
			break;
		default:
			break;
		}
		return null;
	}

	@Override
	public List queryHistoryData(DataTypeEnum type, QueryScopeEnum scope) {
		// TODO Auto-generated method stub
		return null;
	}

}
