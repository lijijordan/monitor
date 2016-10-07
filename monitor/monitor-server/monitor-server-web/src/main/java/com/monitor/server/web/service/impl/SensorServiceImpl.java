/**
 * 传感器逻辑层，从设备层获取传感器数据
 * 
 */
package com.monitor.server.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.entity.DataPointsDevInfo;
import com.monitor.server.entity.DataPointsDevStatisticsInfo;
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

	/**
	 * 获取某设备某类型传感器当前值
	 */
	@Override
	public ResponseVo<DataPointsDevInfo> getCurSensorValByType(String equID, String sensorType)
			throws BusinessException {

		// 拼接设备服务端URL
		StringBuffer url = new StringBuffer();
		url.append(ConstantObject.DEVICER_REST_URL);
		url.append("getCurrent/");
		url.append(equID);
		url.append("/");
		url.append(sensorType);

		// 设置返回默认值
		ResponseVo<DataPointsDevInfo> result = new ResponseVo<DataPointsDevInfo>();
		result.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		result.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 返回值为空，值设置为"0"
		DataPointsDevInfo dataPointsActiveInfo = new DataPointsDevInfo();
		dataPointsActiveInfo.setValue("0");
		result.setContent(dataPointsActiveInfo);

		try {

			// 从设备服务端获取数据
			@SuppressWarnings({ "rawtypes" })
			ResponseVo responseVo = restTemplate.getForObject(url.toString(), ResponseVo.class);

			if (responseVo.getStatus().equalsIgnoreCase("200")) {

				result.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
				result.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());

				if (responseVo.getContent() != null) {
					JSONObject obj = new JSONObject().fromObject(responseVo.getContent());
					result.setContent((DataPointsDevInfo) JSONObject.toBean(obj, DataPointsDevInfo.class));
				}
			}

		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DevAppError.getErrorCode(),
					ErrorCodeMessEnum.DevAppError.getErrorMessage(), e);
		}

		return result;
	}

	/**
	 * 获取某设备某类型传感器一段之间值
	 */
	@Override
	public ResponseVo<List<DataPointsDevStatisticsInfo>> getSensorValsByPeriod(String equID, String sensorType,
			String timePeriod) throws BusinessException {

		// 拼接设备服务端URL
		StringBuffer url = new StringBuffer();
		url.append(ConstantObject.DEVICER_REST_URL);
		url.append("getList/");
		url.append(equID);
		url.append("/");
		url.append(sensorType);
		url.append("/");
		url.append(timePeriod);

		// 设置返回默认值
		ResponseVo<List<DataPointsDevStatisticsInfo>> result = new ResponseVo<List<DataPointsDevStatisticsInfo>>();
		result.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		result.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		// 返回值为空，设置为"0"
		List<DataPointsDevStatisticsInfo> plist = new ArrayList<DataPointsDevStatisticsInfo>();
		DataPointsDevStatisticsInfo dataPointsStatisticsInfo = new DataPointsDevStatisticsInfo();
		dataPointsStatisticsInfo.setMaxvalue("0");
		dataPointsStatisticsInfo.setMinvalue("0");
		dataPointsStatisticsInfo.setValue("0");
		plist.add(dataPointsStatisticsInfo);
		result.setContent(plist);

		try {

			// 从设备服务端获取数据
			@SuppressWarnings({ "rawtypes" })
			ResponseVo responseVo = restTemplate.getForObject(url.toString(), ResponseVo.class);

			if (responseVo.getStatus().equalsIgnoreCase("200")) {

				result.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
				result.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());

				if (responseVo.getContent() != null) {
					JSONArray jsonArray = JSONArray.fromObject(responseVo.getContent().toString());
					List<DataPointsDevStatisticsInfo> tempList = (List<DataPointsDevStatisticsInfo>) jsonArray
							.toList(jsonArray, DataPointsDevStatisticsInfo.class);
					result.setContent(tempList);
				}
			}

		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DevAppError.getErrorCode(),
					ErrorCodeMessEnum.DevAppError.getErrorMessage(), e);
		}

		return result;
	}

}
