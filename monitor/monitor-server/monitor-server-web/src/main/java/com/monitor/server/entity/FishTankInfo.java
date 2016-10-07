/**
 * 鱼缸信息实体
 */
package com.monitor.server.entity;

import java.io.Serializable;

/**
 * @author yinhong
 *
 */
public class FishTankInfo implements Serializable {

	private static final long serialVersionUID = -8794131464005018156L;
	private int id;
	private int length;
	private int width;
	private int height;
	private int volume;
	private String userAccount;
	private String type;
	private String tankCreateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTankCreateTime() {
		return tankCreateTime;
	}

	public void setTankCreateTime(String tankCreateTime) {
		this.tankCreateTime = tankCreateTime;
	}

}
