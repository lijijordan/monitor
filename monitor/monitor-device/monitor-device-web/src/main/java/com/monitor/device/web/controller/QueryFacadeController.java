package com.monitor.device.web.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.define.DataTypeEnum;
import com.monitor.common.define.QueryScopeEnum;
import com.monitor.common.define.ResponseEnum;
import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.DataQueryVo;
import com.monitor.common.vo.ResponseVo;
import com.monitor.device.web.service.IDataQueryService;
import com.monitor.device.web.service.ITemperatureService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/datafacade")
public class QueryFacadeController {
	@Resource
	private ITemperatureService service;
	@Resource
	private IDataQueryService queryService;

	public QueryFacadeController() {
		// TODO Auto-generated constructor stub
	}

	private ResponseVo<Object> response = new ResponseVo<Object>();

	@RequestMapping(value = "/getList/{devicesn}/{dataType}/{queryScope}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "根据设备ID，数据类型，查询范围获取历史数据", httpMethod = "GET", response = ResponseVo.class, notes = "根据设备ID，数据类型，查询范围获取历史数据")
	@ResponseBody
	public ResponseVo<Object> getList(
			@ApiParam(required = true, name = "devicesn", value = "设备ID") @PathVariable("devicesn") String devicesn,
			@ApiParam(required = true, name = "dataType", value = "数据类型，枚举：Temperature, PH, Salinity, TDS, Light") @PathVariable("dataType") String dataType,
			@ApiParam(required = true, name = "queryScope", value = "查询范围，枚举：Day,Week,Month") @PathVariable("queryScope") String queryScope) {

		System.out.println(devicesn);
		System.out.println(dataType);
		System.out.println(queryScope);
		if (StringUtils.isEmpty(devicesn) || StringUtils.isEmpty(dataType)
				|| StringUtils.isEmpty(queryScope)) {
			response.setStatus(ResponseEnum.PARAMNULL.getStatus());
			response.setMessage(ResponseEnum.PARAMNULL.getMessage());
			return response;
		}
		DataQueryVo vo = new DataQueryVo();
		DataTypeEnum typeEnum = DataTypeEnum.fromString(dataType);
		QueryScopeEnum scopeEnum = QueryScopeEnum.fromString(queryScope);
		if (null == typeEnum || null == scopeEnum) {
			response.setStatus(ResponseEnum.PARAMCONVERTERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMCONVERTERROR.getMessage());
			return response;
		}
		try {
			vo.setDataType(typeEnum.getKey());
			vo.setDataTypeText(typeEnum.getVal());
			vo.setQueryScope(scopeEnum.getKey());
			vo.setQueryScopeText(scopeEnum.getVal());
			vo.setDeviceSn(devicesn);
			List<DataPointsStatisticsInfo> datas = queryService
					.queryHistoryData(vo);

			response.setContent(datas);
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
			response.setStatus(ResponseEnum.SUCCESS.getStatus());
			return response;
		} catch (Exception ex) {
			response.setStatus(ResponseEnum.SYSEXCEPTION.getStatus());
			response.setMessage(ex.getMessage());
			return response;
		}
	}

	@RequestMapping(value = "/getList/{devicesn}/{dataType}/{queryScope}/{startTime}/{endTime}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "根据设备ID，数据类型，查询范围，时间获取历史数据", httpMethod = "GET", response = ResponseVo.class, notes = "根据设备ID，数据类型，查询范围，时间获取历史数据")
	@ResponseBody
	public ResponseVo<Object> getListByTime(
			@ApiParam(required = true, name = "devicesn", value = "设备ID") @PathVariable("devicesn") String devicesn,
			@ApiParam(required = true, name = "dataType", value = "数据类型，枚举：Temperature, PH, Salinity, TDS, Light") @PathVariable("dataType") String dataType,
			@ApiParam(required = true, name = "queryScope", value = "查询范围，枚举：Day,Week,Month") @PathVariable("queryScope") String queryScope,
			@ApiParam(required = true, name = "startTime", value = "开始时间，yyyy-MM-dd hh:mm:ss") @PathVariable("startTime") String startTime,
			@ApiParam(required = true, name = "endTime", value = "结束时间，yyyy-MM-dd hh:mm:ss") @PathVariable("endTime") String endTime) {

		System.out.println(devicesn);
		System.out.println(dataType);
		System.out.println(queryScope);
		System.out.println(startTime);
		System.out.println(endTime);
		if (StringUtils.isEmpty(devicesn) || StringUtils.isEmpty(dataType)
				|| StringUtils.isEmpty(queryScope)
				|| StringUtils.isEmpty(startTime)
				|| StringUtils.isEmpty(endTime)) {
			response.setStatus(ResponseEnum.PARAMNULL.getStatus());
			response.setMessage(ResponseEnum.PARAMNULL.getMessage());
			return response;
		}
		DataQueryVo vo = new DataQueryVo();
		DataTypeEnum typeEnum = DataTypeEnum.fromString(dataType);
		QueryScopeEnum scopeEnum = QueryScopeEnum.fromString(queryScope);
		if (null == typeEnum || null == scopeEnum) {
			response.setStatus(ResponseEnum.PARAMCONVERTERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMCONVERTERROR.getMessage());
			return response;
		}
		Date dtStart, dtEnd = null;
		try {
			dtStart = DateUtils.parseDate(startTime,
					new String[] { "yyyy-MM-dd hh:mm:ss" });
			dtEnd = DateUtils.parseDate(endTime,
					new String[] { "yyyy-MM-dd hh:mm:ss" });
		} catch (ParseException e) {
			response.setStatus(ResponseEnum.PARAMDATEFORMATERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMDATEFORMATERROR.getMessage());
			return response;
		}
		try {
			vo.setDataType(typeEnum.getKey());
			vo.setDataTypeText(typeEnum.getVal());
			vo.setQueryScope(scopeEnum.getKey());
			vo.setQueryScopeText(scopeEnum.getVal());
			vo.setDeviceSn(devicesn);
			vo.setStartTime(dtStart);
			vo.setEndTime(dtEnd);
			List<DataPointsStatisticsInfo> datas = queryService
					.queryHistoryData(vo);

			response.setContent(datas);
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
			response.setStatus(ResponseEnum.SUCCESS.getStatus());
			return response;
		} catch (Exception ex) {
			response.setStatus(ResponseEnum.SYSEXCEPTION.getStatus());
			response.setMessage(ex.getMessage());
			return response;
		}
	}

	@RequestMapping(value = "/getCurrent/{devicesn}/{dataType}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据设备ID，数据类型，查询当前数据", httpMethod = "GET", response = ResponseVo.class, notes = "根据设备ID，数据类型，查询当前数据")
	public ResponseVo<Object> getCurrent(
			@ApiParam(required = true, name = "devicesn", value = "设备ID") @PathVariable("devicesn") String devicesn,
			@ApiParam(required = true, name = "dataType", value = "数据类型，枚举：Temperature, PH, Salinity, TDS, Light") @PathVariable("dataType") String dataType) {
		System.out.println(devicesn);
		System.out.println(dataType);
		if (StringUtils.isEmpty(devicesn) || StringUtils.isEmpty(dataType)) {
			response.setStatus(ResponseEnum.PARAMNULL.getStatus());
			response.setMessage(ResponseEnum.PARAMNULL.getMessage());
			return response;
		}

		DataQueryVo vo = new DataQueryVo();
		vo.setDataType(DataTypeEnum.fromString(dataType).getKey());
		vo.setDeviceSn(devicesn);

		DataPointsActiveInfo data = (DataPointsActiveInfo) queryService
				.queryCurrentData(vo);
		try {
			response.setContent(data);
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
			response.setStatus(ResponseEnum.SUCCESS.getStatus());
			return response;
		} catch (Exception ex) {
			response.setStatus(ResponseEnum.SYSEXCEPTION.getStatus());
			response.setMessage(ex.getMessage());
			return response;
		}
	}
}
