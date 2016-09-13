/**
 * 告警逻辑层
 */
package com.monitor.server.web.service;

import java.util.List;

import com.monitor.server.entity.AlarmInfo;

/**
 * @author yinhong
 *
 */
public interface AlarmService {

	public AlarmInfo selectAlarmById(int id);

	public List<AlarmInfo> selectAlarmsByUserID(int id);

	public int createAlarm(AlarmInfo alarmInfo);

	public int updateAlarm(AlarmInfo alarmInfo);

	public int deleteAlarm(int id);
}
