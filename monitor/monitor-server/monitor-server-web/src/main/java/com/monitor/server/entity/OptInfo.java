/**
 * 用户重要操作信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yinhong
 *
 */
public class OptInfo implements Serializable {

	private static final long serialVersionUID = 5020726058677765892L;
	private int id;
	private int optType;
	private int optObject;
	private int result;
	private Date createTime;

	private UserInfo userInfo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOptType() {
		return optType;
	}

	public void setOptType(int optType) {
		this.optType = optType;
	}

	public int getOptObject() {
		return optObject;
	}

	public void setOptObject(int optObject) {
		this.optObject = optObject;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}


}
