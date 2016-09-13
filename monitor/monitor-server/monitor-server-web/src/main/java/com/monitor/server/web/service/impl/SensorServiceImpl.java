/**
 * 传感器逻辑层
 * 
 */
package com.monitor.server.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monitor.common.model.DataPointsInfo;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.StaticFinalStr;
import com.monitor.server.web.service.SensorService;

/**
 * @author yinhong
 *
 */
@Service("sensorService")
public class SensorServiceImpl implements SensorService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseVo<DataPointsInfo> getSensorCurVal(String equID, String sensorType) {

		StringBuffer url = new StringBuffer();
		url.append(StaticFinalStr.DEVICERESTURL);
		url.append("getCurrent/");
		url.append(equID);
		url.append("/");
		url.append(sensorType);

		@SuppressWarnings("unchecked")
		ResponseVo<DataPointsInfo> responseVo = restTemplate.getForObject(url.toString(), ResponseVo.class);

		return responseVo;
	}

	@Override
	public ResponseVo<DataPointsInfo> getSensorVals(String equID, String sensorType,
			String timePeriod) {
		StringBuffer url = new StringBuffer();
		url.append(StaticFinalStr.DEVICERESTURL);
		url.append("getList/");
		url.append(equID);
		url.append("/");
		url.append(sensorType);
		url.append("/");
		url.append(timePeriod);

		@SuppressWarnings("unchecked")
		ResponseVo<DataPointsInfo> responseVo = restTemplate.getForObject(url.toString(), ResponseVo.class);

		return responseVo;
	}

}
