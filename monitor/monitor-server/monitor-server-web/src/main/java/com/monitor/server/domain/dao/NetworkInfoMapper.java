package com.monitor.server.domain.dao;

import org.springframework.stereotype.Repository;

import com.monitor.server.entity.NetworkInfo;

/**
 * @Description: 设备联网的网络配置信息数据库操作接口
 */
@Repository
public interface NetworkInfoMapper {
  int deleteByPrimaryKey(Long id);

  int deleteByAccout(String account);

  int insert(NetworkInfo record);

  int insertSelective(NetworkInfo record);

  NetworkInfo selectByPrimaryKey(Long id);

  NetworkInfo selectBySSIDAccount(String userAccount, String ssid);

  int updateByPrimaryKeySelective(NetworkInfo record);

  int updateByPrimaryKey(NetworkInfo record);
}
