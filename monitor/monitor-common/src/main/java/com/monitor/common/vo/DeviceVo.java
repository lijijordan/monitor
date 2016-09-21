package com.monitor.common.vo;

import java.util.Date;

public class DeviceVo {

	public DeviceVo() {
		// TODO Auto-generated constructor stub
	}

	private int id;

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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Date getRegisttime() {
		return registtime;
	}

	public void setRegisttime(Date registtime) {
		this.registtime = registtime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	private String sn;
	private String alias;
	private String remark;
	private int type;
	private Date registtime;
	private int status;
}
