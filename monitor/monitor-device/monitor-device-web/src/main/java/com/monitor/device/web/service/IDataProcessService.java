package com.monitor.device.web.service;

import org.springframework.stereotype.Service;

@Service
public interface IDataProcessService<T> {
	void Add(T obj);

	T Get(int id);

	T GetCurrent();
}
