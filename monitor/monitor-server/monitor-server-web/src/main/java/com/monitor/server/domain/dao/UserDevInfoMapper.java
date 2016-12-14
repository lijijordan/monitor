package com.monitor.server.domain.dao;

import org.springframework.stereotype.Repository;
import com.monitor.server.entity.UserDevInfo;

/**
 * @Description: 用户设备关系数据库操作接口
 */
@Repository
public interface UserDevInfoMapper {
  int deleteByPrimaryKey(Long id);

  int deleteByAccount(String userAccount);

  int insert(UserDevInfo record);

  int insertSelective(UserDevInfo record);

  UserDevInfo selectByPrimaryKey(Long id);

  int updateByPrimaryKeySelective(UserDevInfo record);

  int updateByPrimaryKey(UserDevInfo record);

  UserDevInfo selectByUserAccount(String userAccount);
}
