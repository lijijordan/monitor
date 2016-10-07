/**
 * 告警逻辑层
 */
package com.monitor.server.web.service;

import java.util.List;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.entity.AlarmInfo;

/**
 * @author yinhong
 *
 */
public interface AlarmService {

	public List<AlarmInfo> getAllSensorThresholds(String userAccount, String devSN) throws BusinessException;

	public AlarmInfo setSensorThreshold(AlarmInfo AlarmInfo) throws BusinessException;

}
