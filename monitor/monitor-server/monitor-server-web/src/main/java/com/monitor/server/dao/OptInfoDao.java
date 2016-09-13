/**
 * 用户重要操作信息 数据库操作
 */
package com.monitor.server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monitor.server.entity.OptInfo;

/**
 * @author yinhong
 *
 */
@Repository
public class OptInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public OptInfo selectOptInfoById(int id) {
		return (OptInfo) sqlSessionTemplate.selectOne("BaseInfoMapper.getOptInfoById", id);
	}

	@SuppressWarnings("unchecked")
	public List<OptInfo> selectOptInfosByUserID(int id) {

		@SuppressWarnings("rawtypes")
		List optInfoList = sqlSessionTemplate.selectList("BaseInfoMapper.selectOptInfosByUserID", id);

		return (List<OptInfo>) optInfoList;
	}

	public int createOptInfo(OptInfo optInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createOptInfo", optInfo);
	}

}
