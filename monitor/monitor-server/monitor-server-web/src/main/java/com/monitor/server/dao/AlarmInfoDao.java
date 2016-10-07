/**
 * 告警信息数据库操作
 */
package com.monitor.server.dao;

import java.util.HashMap;
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

	public List<AlarmInfo> getAllAlarmByUserAccountDevSN(String userAccount, String devSN) {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("userAccount", userAccount);
		hashMap.put("devSN", devSN);

		List<AlarmInfo> alarmList = sqlSessionTemplate.selectList("BaseInfoMapper.getAllAlarmByUserAccountDevSN",
				hashMap);

		return (List<AlarmInfo>) alarmList;
	}

	public AlarmInfo getAlarmByAccountDevSNType(String userAccount, String devSN, String sensorType) {

		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("userAccount", userAccount);
		hashMap.put("devSN", devSN);
		hashMap.put("sensorType", sensorType);

		AlarmInfo alarmInfo = sqlSessionTemplate.selectOne("BaseInfoMapper.getAlarmByAccountDevSNType", hashMap);

		return alarmInfo;
	}

	public int createAlarm(AlarmInfo alarmInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createAlarm", alarmInfo);
	}

	public int updateAlarm(AlarmInfo alarmInfo) {
		return sqlSessionTemplate.update("BaseInfoMapper.updateAlarm", alarmInfo);
	}

}
