/**
 * 告警逻辑层
 * 
 */
package com.monitor.server.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.dao.AlarmInfoDao;
import com.monitor.server.entity.AlarmInfo;
import com.monitor.server.web.service.AlarmService;

/**
 * @author yinhong
 *
 */
@Service("alarmService")
public class AlarmServiceImpl implements AlarmService {

	@Autowired
	private AlarmInfoDao alarmInfoDao;

	/**
	 * 查询所有传感器阀值
	 */
	@Override
	public List<AlarmInfo> getAllSensorThresholds(String userAccount, String devSN) throws BusinessException {

		List<AlarmInfo> alarmInfoList = null;

		try {
			alarmInfoList = alarmInfoDao.getAllAlarmByUserAccountDevSN(userAccount, devSN);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		return alarmInfoList;
	}

	/**
	 * 设置某类传感器阀值
	 */
	@Override
	public AlarmInfo setSensorThreshold(AlarmInfo alarmInfo) throws BusinessException {

		String userAccount = alarmInfo.getUserAccount();
		String devSN = alarmInfo.getDevSN();
		String sensorType = alarmInfo.getSensorType();

		AlarmInfo sensorAlarm = null;
		int returnNum = 0;

		try {

			sensorAlarm = alarmInfoDao.getAlarmByAccountDevSNType(userAccount, devSN, sensorType);

			// 判断类型传感器是否已经设置阀值，如果没有设置过则直接保存数据，如果已经被设置则更新数据
			if (sensorAlarm == null) {
				returnNum = alarmInfoDao.createAlarm(alarmInfo);
			} else {
				returnNum = alarmInfoDao.updateAlarm(alarmInfo);
			}

		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		if (returnNum != 1) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage());
		}

		return alarmInfo;
	}

}
