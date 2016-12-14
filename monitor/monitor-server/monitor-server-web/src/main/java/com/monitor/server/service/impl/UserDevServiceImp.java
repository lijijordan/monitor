package com.monitor.server.service.impl;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMsgEnum;
import com.monitor.server.domain.dao.UserDevInfoMapper;
import com.monitor.server.domain.dao.UserInfoMapper;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.service.UserDevService;

/**
 * @Description: 用户和设备关系业务操作
 */
@Service("userDevService")
public class UserDevServiceImp implements UserDevService {

  private static final Logger logger = LoggerFactory.getLogger(UserDevServiceImp.class);

  @Autowired
  private UserDevInfoMapper userDevInfoMapper;
  @Autowired
  private UserInfoMapper userInfoMapper;

  /*
   * @Description: 绑定用户和设备
   * 
   * @param userDevInfo
   * 
   * @return
   * 
   * @see
   * com.monitor.server.service.UserDevService#bindUserDev(com.monitor.server.entity.UserDevInfo)
   */
  @Override
  public UserDevInfo bindUserDev(UserDevInfo userDevInfo) throws BusinessException {

    if (userDevInfo == null) {
      throw new BusinessException(ErrorCodeMsgEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMsgEnum.ParamsIsBlank.getErrorMessage());
    }

    int saveResult = 0;

    // 校验
    boolean userIsExisted = checkUserIsExisted(userDevInfo.getUserAccount());
    boolean isBinded = checkUserDevIsBinded(userDevInfo.getUserAccount(), userDevInfo.getDevSn());

    if (!userIsExisted) {
      throw new BusinessException(ErrorCodeMsgEnum.AccountNotExisted.getErrorCode(),
          ErrorCodeMsgEnum.AccountNotExisted.getErrorMessage());
    }

    if (isBinded) {
      throw new BusinessException(ErrorCodeMsgEnum.DevBinded.getErrorCode(),
          ErrorCodeMsgEnum.DevBinded.getErrorMessage());
    }

    // 保存
    try {
      saveResult = userDevInfoMapper.insert(userDevInfo);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMsgEnum.DatabaseError.getErrorCode(),
          ErrorCodeMsgEnum.DatabaseError.getErrorMessage(), e);
    }

    if (saveResult != 1) {
      throw new BusinessException(ErrorCodeMsgEnum.DatabaseError.getErrorCode(),
          ErrorCodeMsgEnum.DatabaseError.getErrorMessage());
    }

    return userDevInfo;
  }

  /**
   * 检查用户名是否已经被注册过
   * 
   * @param account
   * @return
   */
  private boolean checkUserIsExisted(String account) throws BusinessException {

    if (StringUtils.isBlank(account)) {
      throw new BusinessException(ErrorCodeMsgEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMsgEnum.ParamsIsBlank.getErrorMessage());
    }

    boolean isExisted = true;

    try {
      UserInfo userInfo = userInfoMapper.selectByAccount(account);
      if (userInfo == null) {
        isExisted = false;
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMsgEnum.DatabaseError.getErrorCode(),
          ErrorCodeMsgEnum.DatabaseError.getErrorMessage(), e);
    }

    return isExisted;
  }

  /**
   * 校验用户和设备是否已经绑定
   * 
   * @param userDevInfo
   * @return
   */
  public boolean checkUserDevIsBinded(String userAccount, String devSN) throws BusinessException {

    if (StringUtils.isBlank(userAccount) || StringUtils.isBlank(devSN)) {
      throw new BusinessException(ErrorCodeMsgEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMsgEnum.ParamsIsBlank.getErrorMessage());
    }

    boolean isExisted = true;

    try {
      UserDevInfo returnValue = userDevInfoMapper.selectByUserAccount(userAccount);
      if (returnValue == null) {
        isExisted = false;
      } else if (!returnValue.getDevSn().equalsIgnoreCase(devSN)) {
        isExisted = false;
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMsgEnum.DatabaseError.getErrorCode(),
          ErrorCodeMsgEnum.DatabaseError.getErrorMessage(), e);
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
   * @throws BusinessException
   * 
   * @see com.monitor.server.service.UserDevService#selectByUserAccount(java.lang.String)
   */
  @Override
  public UserDevInfo selectByUserAccount(String account) throws BusinessException {

    if (StringUtils.isBlank(account)) {
      throw new BusinessException(ErrorCodeMsgEnum.ParamsIsBlank.getErrorCode(),
          ErrorCodeMsgEnum.ParamsIsBlank.getErrorMessage());
    }

    UserDevInfo userDevInfo = null;

    try {
      userDevInfo = userDevInfoMapper.selectByUserAccount(account);
      if (userDevInfo == null) {
        throw new BusinessException(ErrorCodeMsgEnum.UserDevNoBinded.getErrorCode(),
            ErrorCodeMsgEnum.UserDevNoBinded.getErrorMessage());
      }
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
      throw new BusinessException(ErrorCodeMsgEnum.DatabaseError.getErrorCode(),
          ErrorCodeMsgEnum.DatabaseError.getErrorMessage(), e);
    }

    return userDevInfo;
  }

}
