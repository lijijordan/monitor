package com.monitor.device.web.service;

import java.util.List;

import com.monitor.common.define.DataTypeEnum;
import com.monitor.common.define.QueryScopeEnum;

public interface IDataQueryService<T> {
	T queryCurrentData(DataTypeEnum type);

	List<T> queryHistoryData(DataTypeEnum type, QueryScopeEnum scope);
}
