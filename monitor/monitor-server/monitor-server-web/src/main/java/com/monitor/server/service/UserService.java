/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 用户业务接口
 * @author: yinhong
 * @date: 2016年12月1日 下午5:42:49
 * @version: V1.0
 */
package com.monitor.server.service;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.entity.NetworkInfo;
import com.monitor.server.entity.UserInfo;

/**
 * @Description: 用户业务接口
 */
public interface UserService {

  UserInfo addUser(UserInfo user) throws BusinessException;

  void deleteUser(String account) throws BusinessException;

  UserInfo getUserByAccount(String account) throws BusinessException;

  NetworkInfo addNetwork(NetworkInfo networkInfo) throws BusinessException;

  String login(String account, String password) throws BusinessException;

}
