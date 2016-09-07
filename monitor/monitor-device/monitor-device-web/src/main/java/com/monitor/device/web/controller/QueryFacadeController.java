package com.monitor.device.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.device.web.common.ResponseVo;
import com.monitor.device.web.define.DataTypeEnum;
import com.monitor.device.web.define.ResponseEnum;
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

	@RequestMapping(value = "/getList")
	@ResponseBody
	public ResponseVo<Object> getList(HttpServletRequest request) {
		String uniqid = request.getParameter("uniqid");
		String dataType = request.getParameter("datatype");
		String queryScope = request.getParameter("queryscope");
		System.out.println(uniqid);
		System.out.println(dataType);
		System.out.println(queryScope);
		if (StringUtils.isEmpty(uniqid) || StringUtils.isEmpty(dataType)
				|| StringUtils.isEmpty(queryScope)) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMERROR.getMessage());
			return response;
		}
		List<TemperatureInfo> temps = new ArrayList<TemperatureInfo>();
		for (int i = 0; i < 100; i++) {
			TemperatureInfo t = new TemperatureInfo();
			t.setFishEquId(Integer.valueOf(uniqid));
			t.setTemperatureId(i);
			t.setTemperatureTime(new Date());
			t.setTemperatureValue("25");
			temps.add(t);
		}
		try {
			response.setContent(temps);
			response.setMessage(ResponseEnum.SUCCESS.getMessage());
			response.setStatus(ResponseEnum.SUCCESS.getStatus());
			return response;
		} catch (Exception ex) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ex.getMessage());
			return response;
		}
	}

	@RequestMapping(value = "/getCurrent")
	@ResponseBody
	public ResponseVo<Object> getCurrent(HttpServletRequest request) {
		String uniqid = request.getParameter("uniqid");
		String dataType = request.getParameter("datatype");
		System.out.println(uniqid);
		System.out.println(dataType);
		if (StringUtils.isEmpty(uniqid) || StringUtils.isEmpty(dataType)) {
			response.setStatus(ResponseEnum.PARAMERROR.getStatus());
			response.setMessage(ResponseEnum.PARAMERROR.getMessage());
			return response;
		}

		TemperatureInfo tCurrent = (TemperatureInfo) queryService
				.queryCurrentData(DataTypeEnum.fromString(dataType));
		try {
			response.setContent(tCurrent);
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
