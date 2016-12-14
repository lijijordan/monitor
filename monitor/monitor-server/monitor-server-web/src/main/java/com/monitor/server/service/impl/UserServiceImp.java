/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 用户业务类
 * @author: yinhong
 * @date: 2016年12月1日 下午5:47:04
 * @version: V1.0
 */
package com.monitor.server.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.domain.dao.NetworkInfoMapper;
import com.monitor.server.domain.dao.UserDevInfoMapper;
import com.monitor.server.domain.dao.UserInfoMapper;
import com.monitor.server.entity.NetworkInfo;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.service.UserService;

/**
 * @Description: 用户业务类
 */
@Service("userService")
public class UserServiceImp implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

  @Autowired
  private UserInfoMapper userInfoMapper;
  @Autowired
  private NetworkInfoMapper networkInfoMapper;
  @Autowired
  private UserDevInfoMapper userDevInfoMapper;

  /**
   * 注册用户
   */
  @Override
  @Transactional
  public UserInfo addUser(UserInfo userInfo) throws BusinessException {

    if (userInfo == null) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

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
      saveResult = userInfoMapper.insert(userInfo);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
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
  public NetworkInfo addNetwork(NetworkInfo networkInfo) throws BusinessException {

    if (networkInfo == null) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

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
        saveResult = networkInfoMapper.updateByPrimaryKeySelective(networkInfo);
      } else {
        // 保存
        saveResult = networkInfoMapper.insert(networkInfo);
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
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
   * 删除WIFI网络
   */
  private void deleteNetwork(String account) throws BusinessException {

    if (StringUtils.isBlank(account)) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    try {
      networkInfoMapper.deleteByAccout(account);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
          ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
    }

  }

  /**
   * 删除用户和设备之间的邦迪关系
   */
  private void deleteUserDevBind(String account) throws BusinessException {

    if (StringUtils.isBlank(account)) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    try {
      userDevInfoMapper.deleteByAccount(account);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
          ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
    }

  }

  /**
   * 用户登录，返回用户绑定的设备SN
   */
  @Override
  public String login(String account, String password) throws BusinessException {

    if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    UserDevInfo userDevInfo = null;
    String devSN = "";
    UserInfo userInfo = null;

    // 根据用户账号查询用户信息
    try {
      userInfo = userInfoMapper.selectByAccount(account);
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
        userDevInfo = userDevInfoMapper.selectByUserAccount(account);
        if (userDevInfo != null) {
          devSN = userDevInfo.getDevSn();
        }
      } catch (Exception e) {
        logger.error(e.getMessage(), e);
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
   * 检查用户名是否已经被注册过
   * 
   * @param account
   * @return
   */
  private boolean checkUserIsExisted(String account) throws BusinessException {

    if (StringUtils.isBlank(account)) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    boolean isExisted = true;

    try {
      UserInfo userInfo = userInfoMapper.selectByAccount(account);
      if (userInfo == null) {
        isExisted = false;
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
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
  private boolean checkCreateUserInfoIsCompleted(UserInfo userInfo) throws BusinessException {

    if (userInfo == null) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    boolean isCompleted = false;

    String account = userInfo.getAccount();
    String pass = userInfo.getPassword();
    String nickName = userInfo.getNickName();

    if (!StringUtils.isBlank(account) && !StringUtils.isBlank(pass)
        && !StringUtils.isBlank(nickName)) {
      isCompleted = true;
    }

    return isCompleted;
  }

  /**
   * 检查网络输入信息是否正确，是否可以正常联网(待补充)
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
  private boolean checkNetworkIsExisted(NetworkInfo networkInfo) throws BusinessException {

    if (networkInfo == null) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    boolean isExisted = true;

    String userAccount = networkInfo.getUserAccount();
    String SSID = networkInfo.getSsid();

    try {
      NetworkInfo returnValue = networkInfoMapper.selectBySSIDAccount(userAccount, SSID);
      if (returnValue == null) {
        isExisted = false;
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
          ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
    }

    return isExisted;
  }

  /*
   * @Description: TODO
   * 
   * @param account
   * 
   * @return
   * 
   * @see com.monitor.server.service.UserService#deleteUser(java.lang.String)
   */
  @Override
  public void deleteUser(String account) throws BusinessException {

    if (StringUtils.isBlank(account)) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    boolean isExisted = checkUserIsExisted(account);

    if (isExisted) {
      throw new BusinessException(ErrorCodeMessEnum.AccountExisted.getErrorCode(),
          ErrorCodeMessEnum.AccountExisted.getErrorMessage());
    }

    // 删除用户信息
    try {
      userInfoMapper.deleteByAccout(account);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
          ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
    }

    // 删除用户配置网络信息
    deleteNetwork(account);

    // 删除用户和设备的关联信息
    deleteUserDevBind(account);

  }

  /*
   * @Description: TODO
   * 
   * @param account
   * 
   * @return
   * 
   * @see com.monitor.server.service.UserService#getUserByAccount(java.lang.String)
   */
  @Override
  public UserInfo getUserByAccount(String account) throws BusinessException {

    if (StringUtils.isBlank(account)) {
      throw new BusinessException(ErrorCodeMessEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMessEnum.ParamsIsBlank.getErrorMessage());
    }

    UserInfo userInfo = null;

    try {
      userInfo = userInfoMapper.selectByAccount(account);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMessEnum.DatabaseError.getErrorCode(),
          ErrorCodeMessEnum.DatabaseError.getErrorMessage(), e);
    }

    return userInfo;
  }

}
