package com.monitor.server.service;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.entity.UserDevInfo;


public interface UserDevService {

  UserDevInfo bindUserDev(UserDevInfo userDevInfo) throws BusinessException;

  UserDevInfo selectByUserAccount(String account) throws BusinessException;

  boolean checkUserDevIsBinded(String userAccount, String devSN) throws BusinessException;

}
