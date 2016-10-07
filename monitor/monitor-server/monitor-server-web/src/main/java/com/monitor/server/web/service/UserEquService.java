/**
 * 用户设备逻辑层
 */
package com.monitor.server.web.service;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.entity.FishTankInfo;
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

	public String login(String account, String password) throws BusinessException;

	public void logout(UserInfo userInfo) throws BusinessException;

	public void editUserInfo(UserInfo userInfo, FishTankInfo fishTankInfo) throws BusinessException;

	public int createOptInfo(OptInfo optInfo);
}
