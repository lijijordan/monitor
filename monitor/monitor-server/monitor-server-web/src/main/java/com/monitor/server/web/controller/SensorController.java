package com.monitor.server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.device.common.model.DataPointsInfo;
import com.monitor.device.common.vo.ResponseVo;
import com.monitor.server.web.service.SensorService;

/**
 * 传感器Controller
 *
 * @author yinhong
 *
 */
@Controller
@RequestMapping("/sensor")
public class SensorController {

	@Autowired
	private SensorService sensorService;

	public SensorController() {
	}

	/**
	 * 获取传感器当前时间数据
	 * 
	 * @param request
	 *            请求对象
	 * @param equID
	 *            设备ID
	 * @param sensorType
	 *            传感器类型
	 * @return
	 */
	@RequestMapping(value = "/getsensorcurval/{equID}/{sensorType}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsInfo> getSensorCurVal(@PathVariable("equID") String equID,
			@PathVariable("sensorType") String sensorType) {

		ResponseVo<DataPointsInfo> responseVo = sensorService.getSensorCurVal(equID, sensorType);

		return responseVo;
	}

	/**
	 * 获取传感器一段时间的数据
	 * 
	 * @param request
	 *            请求对象
	 * @param equID
	 *            设备ID
	 * @param type
	 *            传感器类型
	 * @param period
	 *            查询数据周期
	 * @return
	 */
	@RequestMapping(value = "/getsensorvals/{equID}/{sensorType}/{timePeriod}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsInfo> getSensorVals(@PathVariable("equID") String equID,
			@PathVariable("sensorType") String sensorType, @PathVariable("timePeriod") String timePeriod) {

		ResponseVo<DataPointsInfo> responseVo = sensorService.getSensorVals(equID, sensorType, timePeriod);

		return responseVo;
	}

}
