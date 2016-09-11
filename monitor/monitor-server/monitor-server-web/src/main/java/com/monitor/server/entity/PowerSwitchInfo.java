/**
 * 插线板信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;

/**
 * @author yinhong
 *
 */
public class PowerSwitchInfo implements Serializable {

	private static final long serialVersionUID = 3286213559713635L;
	private int id;
	private String sn;

	// 每个插口的状态，可以使用";"隔开表示
	private String perStatus;

	private UserInfo userInfo;
	private EquInfo equInfo;

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

	public String getPerStatus() {
		return perStatus;
	}

	public void setPerStatus(String perStatus) {
		this.perStatus = perStatus;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public EquInfo getEquInfo() {
		return equInfo;
	}

	public void setEquInfo(EquInfo equInfo) {
		this.equInfo = equInfo;
	}

}
