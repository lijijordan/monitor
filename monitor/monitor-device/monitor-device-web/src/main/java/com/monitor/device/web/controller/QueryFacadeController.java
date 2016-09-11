package com.monitor.device.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.device.common.vo.ResponseVo;
import com.monitor.device.common.define.DataTypeEnum;
import com.monitor.device.common.define.ResponseEnum;
import com.monitor.device.common.model.DataPointsInfo;
import com.monitor.device.web.model.TemperatureInfo;
import com.monitor.device.web.service.IDataQueryService;
import com.monitor.device.web.service.ITemperatureService;

@Controller
@RequestMapping("/datafacade")
public class QueryFacadeController {
	@Resource
	private ITemperatureService service;
	@Resource
	private IDataQueryService<Object> queryService;

	public QueryFacadeController() {
		// TODO Auto-generated constructor stub
	}

	private ResponseVo<Object> response = new ResponseVo<Object>();

	@RequestMapping(value = "/getList/{uniqid}/{dataType}/{queryScope}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseVo<Object> getList(@PathVariable("uniqid") String uniqid,
			@PathVariable("dataType") String dataType,
			@PathVariable("queryScope") String queryScope) {

		System.out.println(uniqid);
		System.out.println(dataType);
		System.out.println(queryScope);
		if (StringUtils.isEmpty(uniqid) || StringUtils.isEmpty(dataType)
				|| StringUtils.isEmpty(queryScope)) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMERROR.getMessage());
			return response;
		}
		List<DataPointsInfo> datas = new ArrayList<DataPointsInfo>();
		for (int i = 0; i < 100; i++) {
			DataPointsInfo data = new DataPointsInfo();
			switch (DataTypeEnum.fromString(dataType)) {
			case Temperature:
				data.setCollecttime(new Date());
				data.setValue("28");
				break;
			case PH:
				data.setCollecttime(new Date());
				data.setValue("7.1");
				break;
			case WaterLine:
				data.setCollecttime(new Date());
				data.setValue("43");
				break;
			case Conductivity:
				data.setCollecttime(new Date());
				data.setValue("140");
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
	public ResponseVo<Object> getCurrent(@PathVariable("uniqid") String uniqid,
			@PathVariable("dataType") String dataType) {
		System.out.println(uniqid);
		System.out.println(dataType);
		if (StringUtils.isEmpty(uniqid) || StringUtils.isEmpty(dataType)) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMERROR.getMessage());
			return response;
		}

		// TemperatureInfo tCurrent = (TemperatureInfo) queryService
		// .queryCurrentData(DataTypeEnum.fromString(dataType));
		DataPointsInfo data = new DataPointsInfo();
		switch (DataTypeEnum.fromString(dataType)) {
		case Temperature:
			data.setCollecttime(new Date());
			data.setValue("28");
			break;
		case PH:
			data.setCollecttime(new Date());
			data.setValue("7.1");
			break;
		case WaterLine:
			data.setCollecttime(new Date());
			data.setValue("43");
			break;
		case Conductivity:
			data.setCollecttime(new Date());
			data.setValue("140");
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
