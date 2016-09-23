/**
 * 传感器逻辑层
 * 
 */
package com.monitor.server.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.web.service.SensorService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author yinhong
 *
 */
@Service("sensorService")
public class SensorServiceImpl implements SensorService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public ResponseVo<DataPointsActiveInfo> getCurSensorValByType(String equID, String sensorType) {

		StringBuffer url = new StringBuffer();
		url.append(ConstantObject.DEVICER_EST_URL);
		url.append("getCurrent/");
		url.append(equID);
		url.append("/");
		url.append(sensorType);

		@SuppressWarnings({ "rawtypes" })
		ResponseVo responseVo = restTemplate.getForObject(url.toString(), ResponseVo.class);

		ResponseVo<DataPointsActiveInfo> result = new ResponseVo<DataPointsActiveInfo>();
		result.setStatus(responseVo.getStatus());
		result.setMessage(responseVo.getMessage());
		JSONObject obj = new JSONObject().fromObject(responseVo.getContent().toString());
		result.setContent((DataPointsActiveInfo) JSONObject.toBean(obj, DataPointsActiveInfo.class));

		return result;
	}

	@Override
	public ResponseVo<List<DataPointsStatisticsInfo>> getSensorValsByPeriod(String equID, String sensorType,
			String timePeriod) {

		StringBuffer url = new StringBuffer();
		url.append(ConstantObject.DEVICER_EST_URL);
		url.append("getList/");
		url.append(equID);
		url.append("/");
		url.append(sensorType);
		url.append("/");
		url.append(timePeriod);

		@SuppressWarnings({ "rawtypes" })
		ResponseVo responseVo = restTemplate.getForObject(url.toString(), ResponseVo.class);

		ResponseVo<List<DataPointsStatisticsInfo>> result = new ResponseVo<List<DataPointsStatisticsInfo>>();
		result.setStatus(responseVo.getStatus());
		result.setMessage(responseVo.getMessage());
		JSONArray jsonArray = JSONArray.fromObject(responseVo.getContent().toString());
		List<DataPointsStatisticsInfo> plist = jsonArray.toList(jsonArray, DataPointsStatisticsInfo.class);
		result.setContent(plist);

		return result;
	}

}
