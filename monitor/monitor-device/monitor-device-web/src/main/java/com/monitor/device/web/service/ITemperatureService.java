package com.monitor.device.web.service;

import org.springframework.stereotype.Service;

import com.monitor.device.web.model.TemperatureInfo;

@Service
public interface ITemperatureService {
	void Add(TemperatureInfo obj);
	TemperatureInfo Get(int id);
}
