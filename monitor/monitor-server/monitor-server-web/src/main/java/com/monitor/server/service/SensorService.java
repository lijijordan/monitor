/**
 * 传感器逻辑层
 */
package com.monitor.server.service;

import java.util.List;

import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;
import com.monitor.server.entity.DataPointsDevInfo;
import com.monitor.server.entity.DataPointsDevStatisticsInfo;

/**
 * @author yinhong
 *
 */
public interface SensorService {

	/**
	 * 获取传感器当前时间数据
	 * 
	 * @param equID
	 * @param sensorType
	 * @return
	 * @throws BusinessException
	 */
	public ResponseVo<DataPointsDevInfo> getCurSensorValByType(String equID, String sensorType)
			throws BusinessException;

	/**
	 * 获取传感器一段时间的数据
	 * 
	 * @param equID
	 * @param sensorType
	 * @param timePeriod
	 * @return
	 * @throws BusinessException
	 */
	public ResponseVo<List<DataPointsDevStatisticsInfo>> getSensorValsByPeriod(String equID, String sensorType,
			String timePeriod) throws BusinessException;

}
