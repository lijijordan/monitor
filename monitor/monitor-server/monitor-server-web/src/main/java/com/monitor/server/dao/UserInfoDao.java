/**
 * 用户信息数据库操作
 */
package com.monitor.server.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.monitor.server.entity.FishTankInfo;
import com.monitor.server.entity.NetworkInfo;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;

/**
 * @author yinhong
 *
 */
@Repository
public class UserInfoDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public UserInfo selectUserByAccount(String account) {
		return (UserInfo) sqlSessionTemplate.selectOne("BaseInfoMapper.getUserByAccount", account);
	}

	public String getDevSNByUserAccount(String account) {
		return (String) sqlSessionTemplate.selectOne("BaseInfoMapper.getDevSNByUserAccount", account);
	}

	public int createUser(UserInfo userInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createUser", userInfo);
	}

	public int createFishTank(FishTankInfo fishTankInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createFishTank", fishTankInfo);
	}

	public FishTankInfo getFishTankByAccount(String account) {
		return (FishTankInfo) sqlSessionTemplate.selectOne("BaseInfoMapper.getFishTankByAccount", account);
	}

	public int createNetwork(NetworkInfo networkInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createNetwork", networkInfo);
	}

	public int createUserDevLink(UserDevInfo userDevInfo) {
		return sqlSessionTemplate.insert("BaseInfoMapper.createUserDevLink", userDevInfo);
	}

	public int updateUser(UserInfo userInfo) {
		return sqlSessionTemplate.update("BaseInfoMapper.updateUser", userInfo);
	}

	public int updateFishTankByUserAccount(FishTankInfo fishTankInfo) {
		return sqlSessionTemplate.update("BaseInfoMapper.updateFishTankByUserAccount", fishTankInfo);
	}

	public int updateNetwork(NetworkInfo networkInfo) {
		return sqlSessionTemplate.update("BaseInfoMapper.updateNetworkByUserAccount", networkInfo);
	}

	public NetworkInfo getNetworkByAccountSSID(String userAccount, String ssid) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("userAccount", userAccount);
		hashMap.put("ssid", ssid);
		return sqlSessionTemplate.selectOne("BaseInfoMapper.getNetworkByAccountSSID", hashMap);
	}

	public UserDevInfo getBindInfoByAccountSN(String userAccount, String devSN) {
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("userAccount", userAccount);
		hashMap.put("devSN", devSN);
		return sqlSessionTemplate.selectOne("BaseInfoMapper.getBindInfoByAccountSN", hashMap);
	}

}
