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
	private String account;
	private String sn;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

}
