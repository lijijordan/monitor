/**
 * 用户设备逻辑层
 */
package com.monitor.server.web.service;

import java.util.List;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.entity.EquInfo;
import com.monitor.server.entity.NetworkInfo;
import com.monitor.server.entity.OptInfo;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;

/**
 * @author yinhong
 *
 */
public interface UserEquService {

	public UserInfo registerUser(UserInfo userInfo) throws BusinessException;

	public NetworkInfo registerNetwork(NetworkInfo networkInfo) throws BusinessException;

	public UserDevInfo bindUserDev(UserDevInfo userDevInfo) throws BusinessException;

	public String login(String account, String password);

	public void logout(UserInfo userInfo);

	public void editUserInfo(UserInfo userInfo);

	public EquInfo discoverDev();

	public void editEquInfo(EquInfo equInfo);

	public int createEqu(EquInfo equInfo);

	public int updateUser(UserInfo userInfo);

	public int updateEqu(EquInfo equInfo);

	public int deleteUser(int userID);

	public int deleteEqu(int equID);

	public List<UserInfo> selectAllUsers();

	public List<EquInfo> selectAllEqus();

	public int countAllUser();

	public int countAllEqu();

	public EquInfo findEquByID(int equID);

	public int createOptInfo(OptInfo optInfo);
}
