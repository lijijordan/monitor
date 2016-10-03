/**
 * 用户设备逻辑层
 * 
 * 目前按照一个用户只用一套设备，一个家里只有一套设备设计代码
 */
package com.monitor.server.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.dao.EquInfoDao;
import com.monitor.server.dao.OptInfoDao;
import com.monitor.server.dao.UserInfoDao;
import com.monitor.server.entity.EquInfo;
import com.monitor.server.entity.FishTankInfo;
import com.monitor.server.entity.NetworkInfo;
import com.monitor.server.entity.OptInfo;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.web.service.UserEquService;

/**
 * @author yinhong
 *
 */
@Service("userEquService")
public class UserEquServiceImpl implements UserEquService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private EquInfoDao equInfoDao;

	@Autowired
	private OptInfoDao optInfoDao;

	/**
	 * 注册用户
	 */
	@Override
	@Transactional
	public UserInfo registerUser(UserInfo userInfo) throws BusinessException {

		int saveResult = 0;

		// 校验输入条件
		boolean isExisted = checkUserIsExisted(userInfo.getAccount());
		boolean isCompleted = checkUserIsCompleted(userInfo);

		if (isExisted) {
			throw new BusinessException("Account has bean used.");
		}

		if (!isCompleted) {
			throw new BusinessException("User information error.");
		}

		// 保存用户信息
		try {
			saveResult = createUser(userInfo);
		} catch (Exception e) {
			throw new BusinessException("Database error.");
		}

		if (saveResult != 1) {
			throw new BusinessException("Database error.");
		}

		// 记录用户操作记录待增加

		return userInfo;

	}

	/**
	 * 注册网络
	 */
	@Override
	@Transactional
	public NetworkInfo registerNetwork(NetworkInfo networkInfo) throws BusinessException {

		int saveResult = 0;

		// 校验网络配置是否正确
		boolean isCorrect = checkNetworkIsCorrect(networkInfo);

		if (!isCorrect) {
			throw new BusinessException("Network information is wrong.");
		}

		// 保存网络信息
		try {
			saveResult = createNetwork(networkInfo);
		} catch (Exception e) {
			throw new BusinessException("Database error.");
		}

		if (saveResult != 1) {
			throw new BusinessException("Database error.");
		}

		// 记录用户操作记录待增加

		return networkInfo;

	}

	/**
	 * 发现设备
	 */
	@Override
	public EquInfo discoverDev() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 用户登录
	 */
	@Override
	public void login(UserInfo userInfo) {

		/**
		 * 1、通过用户名查询用户和设备信息（查询失败，跳转到密码错误或网络异常页面）
		 * 2、通过设备信息连接网络（连接网络失败，跳转到修改网络SSID和密码页面） 3、跳转到首页
		 */

	}

	/**
	 * 检查用户名是否已经被注册
	 * 
	 * @param account
	 * @return
	 */
	private boolean checkUserIsExisted(String account) {
		// TODO Auto-generated method stub

		return false;
	}

	/**
	 * 检查用户信息是否完整（比填项是否全面填写、校验用户信息是否符合规则）
	 * 
	 * @param userInfo
	 * @return
	 */
	private boolean checkUserIsCompleted(UserInfo userInfo) {

		return true;
	}

	/**
	 * 检查网络输入信息是否正确，是否可以正常联网
	 * 
	 * @param networkInfo
	 * @return
	 */
	private boolean checkNetworkIsCorrect(NetworkInfo networkInfo) {

		return true;
	}

	/**
	 * 注销用户
	 */
	@Override
	public void logout(UserInfo userInfo) {
		// TODO Auto-generated method stub

	}

	/**
	 * 修改用户信息
	 */
	@Override
	public void editUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub

	}

	/**
	 * 修改设备信息（SSID、密码）
	 */
	@Override
	public void editEquInfo(EquInfo equInfo) {
		// TODO Auto-generated method stub

	}

	private int createUser(UserInfo userInfo) {
		return userInfoDao.createUser(userInfo);
	}

	@Override
	public int createFishTank(FishTankInfo fishTackInfo) {
		return userInfoDao.createFishTank(fishTackInfo);
	}

	@Override
	public int createNetwork(NetworkInfo networkInfo) {
		return userInfoDao.createNetwork(networkInfo);
	}

	@Override
	public int createUserDevLink(UserDevInfo userDevInfo) {
		return userInfoDao.createUserDevLink(userDevInfo);
	}

	public String getDevSNByUserAccount(String account) {
		return userInfoDao.getDevSNByUserAccount(account);
	}

	@Override
	public int updateUser(UserInfo userInfo) {
		return userInfoDao.updateUser(userInfo);
	}

	@Override
	public int deleteUser(int userID) {
		return userInfoDao.deleteUser(userID);
	}

	@Override
	public List<UserInfo> selectAllUsers() {
		return userInfoDao.selectAllUser();
	}

	@Override
	public int countAllUser() {
		return userInfoDao.countAllUserNum();
	}

	@Override
	public UserInfo selectUserByAccount(String account) {
		return userInfoDao.selectUserByAccount(account);
	}

	@Override
	public int createEqu(EquInfo equInfo) {
		return equInfoDao.createEqu(equInfo);
	}

	@Override
	public int updateEqu(EquInfo equInfo) {
		return equInfoDao.updateEqu(equInfo);
	}

	@Override
	public int deleteEqu(int equID) {
		return equInfoDao.delete(equID);
	}

	@Override
	public List<EquInfo> selectAllEqus() {
		return equInfoDao.selectAllEqus();
	}

	@Override
	public int countAllEqu() {
		return equInfoDao.countAllEquNum();
	}

	@Override
	public EquInfo findEquByID(int equID) {
		return equInfoDao.selectEquById(equID);
	}

	@Override
	public int createOptInfo(OptInfo optInfo) {
		return optInfoDao.createOptInfo(optInfo);
	}

}
