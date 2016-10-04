/**
 * 传感器逻辑层
 */
package com.monitor.server.web.service;

import java.util.List;

import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;

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
	public ResponseVo<DataPointsActiveInfo> getCurSensorValByType(String equID, String sensorType)
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
	public ResponseVo<List<DataPointsStatisticsInfo>> getSensorValsByPeriod(String equID, String sensorType,
			String timePeriod) throws BusinessException;

}
