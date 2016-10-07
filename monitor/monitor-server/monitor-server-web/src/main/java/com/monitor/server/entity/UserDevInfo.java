/**
 * 用户与设备关联关系信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;

/**
 * @author yinhong
 *
 */
public class UserDevInfo implements Serializable {

	private static final long serialVersionUID = 8993968681015316236L;
	private int id;
	private String userAccount;
	private String devSN;

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

	public String getDevSN() {
		return devSN;
	}

	public void setDevSN(String devSN) {
		this.devSN = devSN;
	}

}
