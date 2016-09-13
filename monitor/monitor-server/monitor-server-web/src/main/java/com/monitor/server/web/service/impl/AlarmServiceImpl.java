/**
 * 告警逻辑层
 * 
 */
package com.monitor.server.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public AlarmInfo selectAlarmById(int id) {
		return alarmInfoDao.selectAlarmById(id);
	}

	@Override
	public List<AlarmInfo> selectAlarmsByUserID(int id) {
		return alarmInfoDao.selectAlarmsByUserID(id);
	}

	@Override
	public int createAlarm(AlarmInfo alarmInfo) {
		return alarmInfoDao.createAlarm(alarmInfo);
	}

	@Override
	public int updateAlarm(AlarmInfo alarmInfo) {
		return alarmInfoDao.updateAlarm(alarmInfo);
	}

	@Override
	public int deleteAlarm(int id) {
		return alarmInfoDao.deleteAlarm(id);
	}

}
