/**
 * 传感器逻辑层
 */
package com.monitor.server.web.service;

import com.monitor.common.model.DataPointsActiveInfo;
import com.monitor.common.model.DataPointsStatisticsInfo;
import com.monitor.common.vo.ResponseVo;

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
	 */
	public ResponseVo<DataPointsActiveInfo> getCurSensorValByType(String equID, String sensorType);

	/**
	 * 获取传感器一段时间的数据
	 * 
	 * @param equID
	 * @param sensorType
	 * @param timePeriod
	 * @return
	 */
	public ResponseVo<DataPointsStatisticsInfo> getSensorValsByPeriod(String equID, String sensorType, String timePeriod);

}
