/**
 * 用户信息数据库操作
 */
package com.monitor.server.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monitor.server.entity.UserInfo;

/**
 * @author yinhong
 *
 */
@Repository
public class UserInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public UserInfo selectUserById(int id) {
		return (UserInfo) sqlSessionTemplate.selectOne("BaseInfoMapper.getUserById", id);
	}

	public int countAllUserNum() {
		Integer countInteger = sqlSessionTemplate.selectOne("BaseInfoMapper.countAllUserNum");
		int count = countInteger.intValue();
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<UserInfo> selectAllUser() {

		@SuppressWarnings("rawtypes")
		List userList = sqlSessionTemplate.selectList("BaseInfoMapper.selectAllUser");

		return (List<UserInfo>) userList;
	}

	public int createUser(UserInfo userInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createUser", userInfo);
	}

	public int updateUser(UserInfo userInfo) {
		return sqlSessionTemplate.update("BaseInfoMapper.updateUser", userInfo);
	}

	public int deleteUser(int id) {
		return sqlSessionTemplate.delete("BaseInfoMapper.deleteUser", id);
	}
}
