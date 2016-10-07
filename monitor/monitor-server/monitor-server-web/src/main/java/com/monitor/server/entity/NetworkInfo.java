/**
 * 网络配置信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;

/**
 * @author yinhong
 *
 */
public class NetworkInfo implements Serializable {

	private static final long serialVersionUID = -5688369055275887970L;
	private int id;
	private String ssid;
	private String password;
	private String userAccount;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
