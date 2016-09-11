/**
 * 温度信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yinhong
 *
 */
public class TempInfo implements Serializable {

	private static final long serialVersionUID = -3710352691464718029L;
	private int id;
	private int value;
	private Date createTime;
	private int userId;
	private int equId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getEquId() {
		return equId;
	}

	public void setEquId(int equId) {
		this.equId = equId;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
