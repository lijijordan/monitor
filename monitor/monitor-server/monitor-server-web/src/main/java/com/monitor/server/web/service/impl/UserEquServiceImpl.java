/**
 * 用户设备逻辑层
 * 
 * 目前按照一个用户只用一套设备
 */
package com.monitor.server.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMessEnum;
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

		// 校验
		boolean isExisted = checkUserIsExisted(userInfo.getAccount());
		boolean isCompleted = checkUserIsCompleted(userInfo);

		if (isExisted) {
			throw new BusinessException(ErrorCodeMessEnum.AccountExisted.getErrorCode(),
					ErrorCodeMessEnum.AccountExisted.getErrorMessage());
		}

		if (!isCompleted) {
			throw new BusinessException(ErrorCodeMessEnum.AccountInfoError.getErrorCode(),
					ErrorCodeMessEnum.AccountInfoError.getErrorMessage());
		}

		// 保存
		try {
			saveResult = createUser(userInfo);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		if (saveResult != 1) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage());
		}

		return userInfo;

	}

	/**
	 * 注册WIFI网络
	 */
	@Override
	@Transactional
	public NetworkInfo registerNetwork(NetworkInfo networkInfo) throws BusinessException {

		int saveResult = 0;

		// 校验
		boolean isCorrect = checkNetworkIsCorrect(networkInfo);
		boolean isExisted = checkNetworkIsExisted(networkInfo);

		if (isExisted) {
			// 更新网络信息
		}

		if (!isCorrect) {
			throw new BusinessException(ErrorCodeMessEnum.NetworkInfoError.getErrorCode(),
					ErrorCodeMessEnum.NetworkInfoError.getErrorMessage());
		}

		// 保存
		try {
			saveResult = createNetwork(networkInfo);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		if (saveResult != 1) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage());
		}

		return networkInfo;

	}

	/**
	 * 绑定用户和设备
	 */
	@Override
	@Transactional
	public UserDevInfo bindUserDev(UserDevInfo userDevInfo) throws BusinessException {

		int saveResult = 0;

		// 校验
		boolean isExisted = checkUserDevIsBinded(userDevInfo);

		if (isExisted) {
			throw new BusinessException(ErrorCodeMessEnum.DevBinded.getErrorCode(),
					ErrorCodeMessEnum.DevBinded.getErrorMessage());
		}

		// 保存
		try {
			saveResult = createUserDevLink(userDevInfo);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		if (saveResult != 1) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage());
		}

		return userDevInfo;

	}

	/**
	 * 用户登录，返回用户绑定的设备SN
	 */
	@Override
	public String login(String account, String password) {

		String devSN = null;
		UserInfo userInfo = null;

		// 根据用户账号查询用户信息
		try {
			userInfo = selectUserByAccount(account);
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		if (userInfo == null) {
			throw new BusinessException(ErrorCodeMessEnum.AccountNotExisted.getErrorCode(),
					ErrorCodeMessEnum.AccountNotExisted.getErrorMessage());
		}

		String userPass = userInfo.getPassword();

		// 如果密码正确，返回用户绑定的设备SN
		if (password.equals(userPass)) {
			try {
				devSN = getDevSNByUserAccount(account);
			} catch (Exception e) {
				throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
						ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
			}
		}

		return devSN;

	}

	/**
	 * 发现设备
	 */
	@Override
	public EquInfo discoverDev() {
		return null;
	}

	/**
	 * 检查用户名是否已经被注册过
	 * 
	 * @param account
	 * @return
	 */
	private boolean checkUserIsExisted(String account) {
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
	 * 检查WIFI网络信息是否已经注册过
	 * 
	 * @param networkInfo
	 * @return
	 */
	private boolean checkNetworkIsExisted(NetworkInfo networkInfo) {
		return false;
	}

	/**
	 * 校验用户和设备是否已经绑定
	 * 
	 * @param userDevInfo
	 * @return
	 */
	private boolean checkUserDevIsBinded(UserDevInfo userDevInfo) {
		return false;
	}

	/**
	 * 注销用户
	 */
	@Override
	public void logout(UserInfo userInfo) {

	}

	/**
	 * 修改用户信息
	 */
	@Override
	public void editUserInfo(UserInfo userInfo) {

	}

	/**
	 * 修改设备信息（SSID、密码）
	 */
	@Override
	public void editEquInfo(EquInfo equInfo) {

	}

	private int createUser(UserInfo userInfo) {
		return userInfoDao.createUser(userInfo);
	}

	private int createFishTank(FishTankInfo fishTackInfo) {
		return userInfoDao.createFishTank(fishTackInfo);
	}

	private int createNetwork(NetworkInfo networkInfo) {
		return userInfoDao.createNetwork(networkInfo);
	}

	private int createUserDevLink(UserDevInfo userDevInfo) {
		return userInfoDao.createUserDevLink(userDevInfo);
	}

	private String getDevSNByUserAccount(String account) {
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

	private UserInfo selectUserByAccount(String account) {
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
