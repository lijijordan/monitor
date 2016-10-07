package com.monitor.server.web.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.entity.AlarmInfo;
import com.monitor.server.web.service.AlarmService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 传感器阀值设置与查询
 * 
 * @author yinhong
 *
 */
@Api(value = "Alarm", description = "Alarm Controller")
@Controller
@RequestMapping(value = "/alarm")
public class AlarmController {

	public static Logger alarmControllerLogger = Logger.getLogger(AlarmController.class);

	@Autowired
	private AlarmService alarmService;

	@ApiOperation(value = "设置传感器阈值", httpMethod = "POST", notes = "保存各种传感器阈值信息")
	@RequestMapping(value = "/setSensorThreshold", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<AlarmInfo> setSensorThreshold(
			@ApiParam(required = true, name = "alarmInfo", value = "传感器阀值信息实体") @RequestBody AlarmInfo alarmInfo) {

		ResponseVo<AlarmInfo> responseVo = new ResponseVo<AlarmInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		try {
			AlarmInfo returnAlarmInfo = alarmService.setSensorThreshold(alarmInfo);

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(returnAlarmInfo);

		} catch (BusinessException e) {
			alarmControllerLogger.error(e.getMessage(), e);

			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

	@ApiOperation(value = "查询所有传感器阈值", httpMethod = "GET", notes = "查询所有传感器阈值")
	@RequestMapping(value = "/getAllSensorThresholds/userAccount/devSN", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<List<AlarmInfo>> getAllSensorThresholds(
			@ApiParam(required = true, name = "userAccount", value = "用户账号") @PathVariable("userAccount") String userAccount,
			@ApiParam(required = true, name = "devSN", value = "设备SN") @PathVariable("devSN") String devSN) {

		ResponseVo<List<AlarmInfo>> responseVo = new ResponseVo<List<AlarmInfo>>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		try {
			List<AlarmInfo> returnAlarmInfoList = alarmService.getAllSensorThresholds(userAccount, devSN);

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(returnAlarmInfoList);

		} catch (BusinessException e) {
			alarmControllerLogger.error(e.getMessage(), e);

			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

}
