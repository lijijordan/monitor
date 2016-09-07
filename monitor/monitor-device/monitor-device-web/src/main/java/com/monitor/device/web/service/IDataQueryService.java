package com.monitor.device.web.service;

import java.util.List;

import com.monitor.device.web.define.DataTypeEnum;
import com.monitor.device.web.define.QueryScopeEnum;

public interface IDataQueryService<T> {
	T queryCurrentData(DataTypeEnum type);

	List<T> queryHistoryData(DataTypeEnum type, QueryScopeEnum scope);
}
