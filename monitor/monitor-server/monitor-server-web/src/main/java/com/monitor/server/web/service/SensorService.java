/**
 * 传感器逻辑层
 */
package com.monitor.server.web.service;

import com.monitor.device.common.model.DataPointsInfo;
import com.monitor.device.common.vo.ResponseVo;

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
	public ResponseVo<DataPointsInfo> getSensorCurVal(String equID, String sensorType);

	/**
	 * 获取传感器一段时间的数据
	 * 
	 * @param equID
	 * @param sensorType
	 * @param timePeriod
	 * @return
	 */
	public ResponseVo<DataPointsInfo> getSensorVals(String equID, String sensorType, String timePeriod);

}
