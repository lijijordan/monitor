/**
 * 告警信息数据库操作
 */
package com.monitor.server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monitor.server.entity.AlarmInfo;

/**
 * @author yinhong
 *
 */
@Repository
public class AlarmInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public AlarmInfo selectAlarmById(int id) {
		return (AlarmInfo) sqlSessionTemplate.selectOne("BaseInfoMapper.getAlarmById", id);
	}

	@SuppressWarnings("unchecked")
	public List<AlarmInfo> selectAlarmsByUserID(int id) {

		@SuppressWarnings("rawtypes")
		List alarmList = sqlSessionTemplate.selectList("BaseInfoMapper.getAlarmsByUserID", id);

		return (List<AlarmInfo>) alarmList;
	}

	public int createAlarm(AlarmInfo alarmInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createAlarm", alarmInfo);
	}

	public int updateAlarm(AlarmInfo alarmInfo) {
		return sqlSessionTemplate.update("BaseInfoMapper.updateAlarm", alarmInfo);
	}

	public int deleteAlarm(int id) {
		return sqlSessionTemplate.delete("BaseInfoMapper.deleteAlarm", id);
	}
}
