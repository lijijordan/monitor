/**
 * 设备数据库操作
 */
package com.monitor.server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monitor.server.entity.EquInfo;

/**
 * @author yinhong
 *
 */
@Repository
public class EquInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public EquInfo selectEquById(int id) {
		return (EquInfo) sqlSessionTemplate.selectOne("BaseInfoMapper.getEquById", id);
	}

	public int countAllEquNum() {
		Integer countInteger = sqlSessionTemplate.selectOne("BaseInfoMapper.countAllEquNum");
		int count = countInteger.intValue();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<EquInfo> selectAllEqus() {

		@SuppressWarnings("rawtypes")
		List equList = sqlSessionTemplate.selectList("BaseInfoMapper.selectAllEqus");

		return (List<EquInfo>) equList;
	}

	public int createEqu(EquInfo equInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createEqu", equInfo);
	}

	public int updateEqu(EquInfo equInfo) {
		return sqlSessionTemplate.update("BaseInfoMapper.updateEqu", equInfo);
	}

	public int delete(int id) {
		return sqlSessionTemplate.delete("BaseInfoMapper.deleteEqu", id);
	}
}
