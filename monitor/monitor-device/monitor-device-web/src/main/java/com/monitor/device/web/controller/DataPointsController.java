package com.monitor.device.web.controller;

import javax.annotation.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import com.monitor.common.vo.DataPointsVo;
import com.monitor.device.web.service.IDataPointsService;
import com.wordnik.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/dataPoints")
public class DataPointsController {
	@Resource
	private IDataPointsService service;

	public DataPointsController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "上报数据使用", httpMethod = "POST", response = String.class, notes = "写数据")
	public String add(@RequestBody DataPointsVo vo) {
		try {
			System.out.println(vo.toString());
			service.Add(vo);
			return "succ";
		} catch (Exception ex) {
			return "error" + ex.toString();
		}
	}

}
