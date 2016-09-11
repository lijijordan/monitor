/**
 * 传感器数据库操作
 */
package com.monitor.server.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monitor.server.entity.ConductivityInfo;
import com.monitor.server.entity.PhInfo;
import com.monitor.server.entity.TempInfo;
import com.monitor.server.entity.WaterLineInfo;

/**
 * @author yinhong
 *
 */
@Repository
public class SensorDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@SuppressWarnings({ "rawtypes" })
	public List<PhInfo> selectHistoryPH(HashMap historyPara) {

		List<PhInfo> phList = sqlSessionTemplate.selectList("sensorinfomapper.selectPHHistoryByTime", historyPara);

		return (List<PhInfo>) phList;
	}

	public int createPH(PhInfo phInfo) {
		return sqlSessionTemplate.insert("sensorinfomapper.createPH", phInfo);
	}

	@SuppressWarnings({ "rawtypes" })
	public List<ConductivityInfo> selectHistoryConductivity(HashMap historyPara) {

		List<ConductivityInfo> conductivityList = sqlSessionTemplate
				.selectList("sensorinfomapper.selectConductivityHistoryByTime", historyPara);

		return (List<ConductivityInfo>) conductivityList;
	}

	public int createConductivity(ConductivityInfo ConductivityInfo) {
		return sqlSessionTemplate.insert("sensorinfomapper.createConductivity", ConductivityInfo);
	}

	@SuppressWarnings({ "rawtypes" })
	public List<TempInfo> selectHistoryTemp(HashMap historyPara) {

		List<TempInfo> tempList = sqlSessionTemplate.selectList("sensorinfomapper.selectTempHistoryByTime",
				historyPara);

		return (List<TempInfo>) tempList;
	}

	public int createTemp(TempInfo tempInfo) {
		return sqlSessionTemplate.insert("sensorinfomapper.createTemp", tempInfo);
	}

	@SuppressWarnings({ "rawtypes" })
	public List<WaterLineInfo> selectWaterLineTemp(HashMap historyPara) {

		List<WaterLineInfo> waterLineList = sqlSessionTemplate
				.selectList("sensorinfomapper.selectWaterLineHistoryByTime", historyPara);

		return (List<WaterLineInfo>) waterLineList;
	}

	public int createWaterLine(WaterLineInfo waterLineInfo) {
		return sqlSessionTemplate.insert("sensorinfomapper.createWaterLine", waterLineInfo);
	}

}
