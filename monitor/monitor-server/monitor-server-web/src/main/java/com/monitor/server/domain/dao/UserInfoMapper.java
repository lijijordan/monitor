package com.monitor.server.domain.dao;

import org.springframework.stereotype.Repository;

import com.monitor.server.entity.UserInfo;

/**
 * @Description: 用户信息数据库操作接口
 */
@Repository
public interface UserInfoMapper {
  int deleteByPrimaryKey(Long id);

  int deleteByAccout(String account);

  int insert(UserInfo record);

  int insertSelective(UserInfo record);

  UserInfo selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(UserInfo record);

  int updateByPrimaryKey(UserInfo record);

  UserInfo selectByAccount(String account);
}
