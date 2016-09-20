package com.monitor.server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.model.DataPointsInfo;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.comm.HealthInfo;
import com.monitor.server.comm.SensorInfo;
import com.monitor.server.web.service.SensorService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 传感器Controller
 *
 * @author yinhong
 *
 */
@Api(value = "Sensor", description = "Sensor Controller")
@Controller
@RequestMapping(value = "/sensor")
public class SensorController {

	@Autowired
	private SensorService sensorService;

	@ApiOperation(value = "获取当前所有传感器值", httpMethod = "GET", notes = "获取用户某个设备所有传感器当前值")
	@RequestMapping(value = "/getAllSensorCurVal/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<SensorInfo> getAllSensorCurVal(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		// 根据用户ID检查输入设备ID是否正确（待增加代码）

		// 分别从设备获取各个传感器的值
		ResponseVo<DataPointsInfo> phCurrentValue = getCurSensorValByType(userID, equID, ConstantObject.SENSOR_TYPE_PH);
		ResponseVo<DataPointsInfo> tempCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_TEMPERATURE);
		ResponseVo<DataPointsInfo> salinityCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_SALINITY);
		ResponseVo<DataPointsInfo> lightCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_LIGHT);
		ResponseVo<DataPointsInfo> tdsCurrentValue = getCurSensorValByType(userID, equID,
				ConstantObject.SENSOR_TYPE_TDS);

		// 将各个传感器的值都封装到一个对象
		SensorInfo sensorInfo = new SensorInfo();
		sensorInfo.setPh(phCurrentValue.getContent().getValue());
		sensorInfo.setLight(lightCurrentValue.getContent().getValue());
		sensorInfo.setSalinity(salinityCurrentValue.getContent().getValue());
		sensorInfo.setTemperature(tempCurrentValue.getContent().getValue());
		sensorInfo.setTds(tdsCurrentValue.getContent().getValue());

		@SuppressWarnings({ "unchecked", "rawtypes" })
		ResponseVo<SensorInfo> responseVo = new ResponseVo();
		responseVo.setStatus(phCurrentValue.getStatus());
		responseVo.setMessage(phCurrentValue.getMessage());
		responseVo.setContent(sensorInfo);

		return responseVo;
	}

	/**
	 * 获取用户某个设备某类型传感器当前值
	 * 
	 * @param userID
	 * @param equID
	 * @param sensorType
	 * @return
	 */
	private ResponseVo<DataPointsInfo> getCurSensorValByType(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（ph,temperature,salinity,tds,light）") @PathVariable("sensorType") String sensorType) {

		ResponseVo<DataPointsInfo> responseVo = sensorService.getCurSensorValByType(equID, sensorType);

		return responseVo;
	}

	@ApiOperation(value = "获取一段时间周期传感器值", httpMethod = "GET", notes = "获取用户某个设备某类型传感器一段时间周期值")
	@RequestMapping(value = "/getSensorValsByPeriod/{userID}/{equID}/{sensorType}/{timePeriod}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsInfo> getSensorValsByPeriod(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID,
			@ApiParam(required = true, name = "sensorType", value = "传感器类型（ph,temperature,salinity,tds,light）") @PathVariable("sensorType") String sensorType,
			@ApiParam(required = true, name = "timePeriod", value = "时间周期（day,week,month）") @PathVariable("timePeriod") String timePeriod) {

		ResponseVo<DataPointsInfo> responseVo = sensorService.getSensorValsByPeriod(equID, sensorType, timePeriod);

		return responseVo;
	}

	@ApiOperation(value = "获取传感器健康度", httpMethod = "GET", notes = "获取用户某个设备所有传感器健康度以及综合健康度")
	@RequestMapping(value = "/getAllSensorEntiretyHealth/{userID}/{equID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<HealthInfo> getAllSensorEntiretyHealth(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "equID", value = "设备ID") @PathVariable("equID") String equID) {

		// 查询所有传感器整体综合健康度（待修改）
		HealthInfo healthInfo = new HealthInfo();
		healthInfo.setAverage("99");
		healthInfo.setPh("7");
		healthInfo.setLight("90");
		healthInfo.setSalinity("51");
		healthInfo.setTds("0");
		healthInfo.setTemperature("28");

		@SuppressWarnings({ "unchecked", "rawtypes" })
		ResponseVo<HealthInfo> responseVo = new ResponseVo();
		responseVo.setStatus("0");
		responseVo.setMessage("OK");
		responseVo.setContent(healthInfo);

		return responseVo;
	}

}
