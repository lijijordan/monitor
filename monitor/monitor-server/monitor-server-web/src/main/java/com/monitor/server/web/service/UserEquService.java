/**
 * 用户设备逻辑层
 */
package com.monitor.server.web.service;

import java.util.List;

import com.monitor.server.entity.EquInfo;
import com.monitor.server.entity.OptInfo;
import com.monitor.server.entity.UserInfo;

/**
 * @author yinhong
 *
 */
public interface UserEquService {

	public void checkUserIsExisted(String userName);
	
	public void checkEquIsExisted(String equSN);
	
	public EquInfo discoverEqu();

	public void regUserEqu(UserInfo userInfo, EquInfo equInfo);

	public void login(UserInfo userInfo);
	
	public void logout(UserInfo userInfo);
	
	public void editUserInfo(UserInfo userInfo);
	
	public void editEquInfo(EquInfo equInfo);

	public int createUser(UserInfo userInfo);

	public int createEqu(EquInfo equInfo);

	public int updateUser(UserInfo userInfo);

	public int updateEqu(EquInfo equInfo);

	public int deleteUser(int userID);

	public int deleteEqu(int equID);

	public List<UserInfo> selectAllUsers();

	public List<EquInfo> selectAllEqus();

	public int countAllUser();

	public int countAllEqu();

	public UserInfo findUserByID(int userID);

	public EquInfo findEquByID(int equID);
	
	public int createOptInfo(OptInfo optInfo);
}
