/**
 * 用户设备逻辑层
 * 
 * 目前按照一个用户只用一套设备
 */
package com.monitor.server.web.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.dao.OptInfoDao;
import com.monitor.server.dao.UserInfoDao;
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
		boolean isCompleted = checkCreateUserInfoIsCompleted(userInfo);

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
			saveResult = userInfoDao.createUser(userInfo);
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
		boolean networkIsExisted = checkNetworkIsExisted(networkInfo);
		boolean userIsExisted = checkUserIsExisted(networkInfo.getUserAccount());

		if (!userIsExisted) {
			throw new BusinessException(ErrorCodeMessEnum.AccountNotExisted.getErrorCode(),
					ErrorCodeMessEnum.AccountNotExisted.getErrorMessage());
		}

		if (!isCorrect) {
			throw new BusinessException(ErrorCodeMessEnum.NetworkInfoError.getErrorCode(),
					ErrorCodeMessEnum.NetworkInfoError.getErrorMessage());
		}

		try {
			if (networkIsExisted) {
				// 更新网络信息
				saveResult = userInfoDao.updateNetwork(networkInfo);
			} else {
				// 保存
				saveResult = userInfoDao.createNetwork(networkInfo);
			}
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
		boolean userIsExisted = checkUserIsExisted(userDevInfo.getUserAccount());
		boolean isBinded = checkUserDevIsBinded(userDevInfo);

		if (!userIsExisted) {
			throw new BusinessException(ErrorCodeMessEnum.AccountNotExisted.getErrorCode(),
					ErrorCodeMessEnum.AccountNotExisted.getErrorMessage());
		}

		if (isBinded) {
			throw new BusinessException(ErrorCodeMessEnum.DevBinded.getErrorCode(),
					ErrorCodeMessEnum.DevBinded.getErrorMessage());
		}

		// 保存
		try {
			saveResult = userInfoDao.createUserDevLink(userDevInfo);
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
	public String login(String account, String password) throws BusinessException {

		String devSN = null;
		UserInfo userInfo = null;

		// 根据用户账号查询用户信息
		try {
			userInfo = userInfoDao.selectUserByAccount(account);
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
				devSN = userInfoDao.getDevSNByUserAccount(account);
			} catch (Exception e) {
				throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
						ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
			}
		} else {
			throw new BusinessException(ErrorCodeMessEnum.AccountPasswordError.getErrorCode(),
					ErrorCodeMessEnum.AccountPasswordError.getErrorMessage());
		}

		return devSN;

	}

	/**
	 * 修改用户信息
	 */
	@Override
	public void editUserInfo(UserInfo userInfo, FishTankInfo fishTankInfo) throws BusinessException {

		int result = 0;
		UserInfo returnUserInfo = null;
		FishTankInfo returnfishTankInfo = null;

		// 根据用户账号查询用户信息
		try {
			returnUserInfo = userInfoDao.selectUserByAccount(userInfo.getAccount());
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		if (returnUserInfo == null) {
			throw new BusinessException(ErrorCodeMessEnum.AccountNotExisted.getErrorCode(),
					ErrorCodeMessEnum.AccountNotExisted.getErrorMessage());
		}

		try {

			// 设置需要更新的信息
			returnUserInfo.setCity(userInfo.getCity());
			returnUserInfo.setPhone(userInfo.getPhone());

			// 更新用户信息
			userInfoDao.updateUser(returnUserInfo);

			// 根据用户账号查询用户鱼缸信息
			returnfishTankInfo = userInfoDao.getFishTankByAccount(userInfo.getAccount());

			// 如果没有鱼缸信息则直接保存，如果有则更新
			if (returnfishTankInfo == null) {

				fishTankInfo.setUserAccount(userInfo.getAccount());
				result = userInfoDao.createFishTank(fishTankInfo);

			} else {

				// 设置需要修改的信息
				returnfishTankInfo.setVolume(fishTankInfo.getVolume());
				returnfishTankInfo.setTankCreateTime(fishTankInfo.getTankCreateTime());
				returnfishTankInfo.setType(fishTankInfo.getType());

				result = userInfoDao.updateFishTankByUserAccount(returnfishTankInfo);
			}

		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		if (result != 1) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage());
		}

	}

	/**
	 * 注销用户
	 */
	@Override
	public void logout(UserInfo userInfo) {

	}

	/**
	 * 检查用户名是否已经被注册过
	 * 
	 * @param account
	 * @return
	 */
	private boolean checkUserIsExisted(String account) throws BusinessException {

		boolean isExisted = true;

		try {
			UserInfo userInfo = userInfoDao.selectUserByAccount(account);

			if (userInfo == null) {
				isExisted = false;
			}
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		return isExisted;
	}

	/**
	 * 检查用户信息是否完整（比填项是否全面填写、校验用户信息是否符合规则）
	 * 
	 * @param userInfo
	 * @return
	 */
	private boolean checkCreateUserInfoIsCompleted(UserInfo userInfo) {

		boolean isCompleted = false;

		String account = userInfo.getAccount();
		String pass = userInfo.getPassword();
		String nickName = userInfo.getNickName();

		if (!StringUtils.isBlank(account) && !StringUtils.isBlank(pass) && !StringUtils.isBlank(nickName)) {
			isCompleted = true;
		}

		return isCompleted;
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

		boolean isExisted = true;

		String userAccount = networkInfo.getUserAccount();
		String SSID = networkInfo.getSsid();

		try {
			NetworkInfo returnValue = userInfoDao.getNetworkByAccountSSID(userAccount, SSID);

			if (returnValue == null) {
				isExisted = false;
			}
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		return isExisted;

	}

	/**
	 * 校验用户和设备是否已经绑定
	 * 
	 * @param userDevInfo
	 * @return
	 */
	private boolean checkUserDevIsBinded(UserDevInfo userDevInfo) {

		boolean isExisted = true;

		String userAccount = userDevInfo.getUserAccount();
		String devSN = userDevInfo.getDevSN();

		try {
			UserDevInfo returnValue = userInfoDao.getBindInfoByAccountSN(userAccount, devSN);

			if (returnValue == null) {
				isExisted = false;
			}
		} catch (Exception e) {
			throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
					ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
		}

		return isExisted;

	}

	@Override
	public int createOptInfo(OptInfo optInfo) {
		return optInfoDao.createOptInfo(optInfo);
	}

}
