/**
 * 设备信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;

/**
 * @author yinhong
 *
 */
public class EquInfo implements Serializable {

	private static final long serialVersionUID = -258746641878877563L;

	private int id;
	private String sn;
	private String ssid;
	private String pwd;
	private UserInfo userInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

}
