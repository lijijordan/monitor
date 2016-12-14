package com.monitor.server.entity;

/**
 * @Description: 用户设备关系对象
 */
public class UserDevInfo {
  private Integer id;

  private String userAccount;

  private String devSn;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getUserAccount() {
    return userAccount;
  }

  public void setUserAccount(String userAccount) {
    this.userAccount = userAccount == null ? null : userAccount.trim();
  }

  public String getDevSn() {
    return devSn;
  }

  public void setDevSn(String devSn) {
    this.devSn = devSn == null ? null : devSn.trim();
  }
}
