/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信用户信息
 * @author: yinhong
 * @date: 2016年11月26日 上午11:16:17
 * @version: V1.0
 */
package com.monitor.server.entity;

import com.monitor.server.entity.wx.BaseEntity;

/**
 * @Description: 用户信息
 */
public class UserInfo extends BaseEntity {

  private static final long serialVersionUID = 8524050338159777232L;

  private String nickName;

  private String sex;

  private String city;

  private String province;

  private String country;

  private String account;

  private String headimgurl;

  private String subscribe;

  private String language;

  private String subscribeTime;

  private String remark;

  private String groupid;

  private String password;

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickname) {
    this.nickName = nickname == null ? null : nickname.trim();
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex == null ? null : sex.trim();
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city == null ? null : city.trim();
  }

  public String getProvince() {
    return province;
  }

  public void setProvince(String province) {
    this.province = province == null ? null : province.trim();
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country == null ? null : country.trim();
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account == null ? null : account.trim();
  }

  public String getHeadimgurl() {
    return headimgurl;
  }

  public void setHeadimgurl(String headimgurl) {
    this.headimgurl = headimgurl == null ? null : headimgurl.trim();
  }

  public String getSubscribe() {
    return subscribe;
  }

  public void setSubscribe(String subscribe) {
    this.subscribe = subscribe == null ? null : subscribe.trim();
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language == null ? null : language.trim();
  }

  public String getSubscribeTime() {
    return subscribeTime;
  }

  public void setSubscribeTime(String subscribeTime) {
    this.subscribeTime = subscribeTime == null ? null : subscribeTime.trim();
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  public String getGroupid() {
    return groupid;
  }

  public void setGroupid(String groupid) {
    this.groupid = groupid == null ? null : groupid.trim();
  }
}
