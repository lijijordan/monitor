package com.monitor.device.web.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.device.web.model.TemperatureInfo;
import com.monitor.device.web.service.ITemperatureService;

@Controller
@RequestMapping("/temperature")
public class TemperatureController {
	@Resource
	private ITemperatureService service;

	public TemperatureController() {
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	@ResponseBody
	public TemperatureInfo index() {
		// return service.Get(1);
		return new TemperatureInfo();
	}

	@RequestMapping(value = "/add")
	@ResponseBody
	public String add(HttpServletRequest request) {
		TemperatureInfo obj = new TemperatureInfo();
		obj.setTemperatureValue(request.getParameter("val"));
		System.out.println(request.getParameter("val"));
		System.out.println(request.getParameter("uid"));
		System.out.println(request.getParameter("eid"));
		obj.setTemperatureTime(new Date());
		obj.setUserId(Integer.parseInt(request.getParameter("uid").toString()));
		obj.setFishEquId(Integer.parseInt(request.getParameter("eid")
				.toString()));
		try {
			service.Add(obj);
			return "succ";
		} catch (Exception ex) {
			return "error" + ex.toString();
		}
	}
}
