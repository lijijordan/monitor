package com.monitor.device.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.define.DataTypeEnum;
import com.monitor.common.define.ResponseEnum;
import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsStatisticsInfo;
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

	@RequestMapping(value = "/getList/{uniqid}/{dataType}/{queryScope}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ApiOperation(value = "根据设备ID，数据类型，查询范围获取历史数据", httpMethod = "GET", response = ResponseVo.class, notes = "根据设备ID，数据类型，查询范围获取历史数据")
	@ResponseBody
	public ResponseVo<Object> getList(
			@ApiParam(required = true, name = "uniqid", value = "设备ID") @PathVariable("uniqid") String uniqid,
			@ApiParam(required = true, name = "dataType", value = "数据类型，枚举：Temperature, PH, WaterLine, Conductivity") @PathVariable("dataType") String dataType,
			@ApiParam(required = true, name = "queryScope", value = "查询范围，枚举：Last60Minute, Last24Hour, Last30Day, Last3Months, LastOneYear, All") @PathVariable("queryScope") String queryScope) {

		System.out.println(uniqid);
		System.out.println(dataType);
		System.out.println(queryScope);
		if (StringUtils.isEmpty(uniqid) || StringUtils.isEmpty(dataType)
				|| StringUtils.isEmpty(queryScope)) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMERROR.getMessage());
			return response;
		}
		List<DataPointsStatisticsInfo> datas = new ArrayList<DataPointsStatisticsInfo>();
		for (int i = 0; i < 100; i++) {
			DataPointsStatisticsInfo data = new DataPointsStatisticsInfo();
			switch (DataTypeEnum.fromString(dataType)) {
			case Temperature:
				data.setCollecttime(new Date());
				data.setValue("28");
				data.setMaxvalue("35");
				data.setMinvalue("15");
				break;
			case PH:
				data.setCollecttime(new Date());
				data.setValue("7.1");
				data.setMaxvalue("9");
				data.setMinvalue("5");
				break;
			case Salinity:
				data.setCollecttime(new Date());
				data.setValue("1.025");
				data.setMaxvalue("1.5");
				data.setMinvalue("0.9");
				break;
			case TDS:
				data.setCollecttime(new Date());
				data.setValue("140");
				data.setMaxvalue("330");
				data.setMinvalue("100");
				break;
			case Light:
				data.setCollecttime(new Date());
				data.setValue("80");
				data.setMaxvalue("100");
				data.setMinvalue("50");
				break;

			default:
				break;
			}
			datas.add(data);
		}
		try {
			response.setContent(datas);
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
			response.setStatus(ResponseEnum.SUCCESS.getStatus());
			return response;
		} catch (Exception ex) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ex.getMessage());
			return response;
		}
	}

	@RequestMapping(value = "/getCurrent/{uniqid}/{dataType}", method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation(value = "根据设备ID，数据类型，查询当前数据", httpMethod = "GET", response = ResponseVo.class, notes = "根据设备ID，数据类型，查询当前数据")
	public ResponseVo<Object> getCurrent(
			@ApiParam(required = true, name = "uniqid", value = "设备ID") @PathVariable("uniqid") String uniqid,
			@ApiParam(required = true, name = "dataType", value = "数据类型，枚举：Temperature, PH, WaterLine, Conductivity") @PathVariable("dataType") String dataType) {
		System.out.println(uniqid);
		System.out.println(dataType);
		if (StringUtils.isEmpty(uniqid) || StringUtils.isEmpty(dataType)) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMERROR.getMessage());
			return response;
		}

		// TemperatureInfo tCurrent = (TemperatureInfo) queryService
		// .queryCurrentData(DataTypeEnum.fromString(dataType));
		DataPointsActiveInfo data = new DataPointsActiveInfo();
		switch (DataTypeEnum.fromString(dataType)) {
		case Temperature:
			data.setCollecttime(new Date());
			data.setValue("28");
			break;
		case PH:
			data.setCollecttime(new Date());
			data.setValue("7.1");
			break;
		case Salinity:
			data.setCollecttime(new Date());
			data.setValue("1.026");
			break;
		case TDS:
			data.setCollecttime(new Date());
			data.setValue("140");
			break;
		case Light:
			data.setCollecttime(new Date());
			data.setValue("85");
			break;
		default:
			break;
		}
		try {
			response.setContent(data);
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
			response.setStatus(ResponseEnum.SUCCESS.getStatus());
			return response;
		} catch (Exception ex) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ex.getMessage());
			return response;
		}
	}

}
