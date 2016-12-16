package com.monitor.server.entity.dev;

import java.io.Serializable;

/**
 * 返回给UI的数据对象
 * 
 * @author yinhong
 *
 */
public class DataPointInfo implements Serializable {

	private static final long serialVersionUID = -5411636903638254520L;

	private Long collecttime;

	private String value;

	public Long getCollecttime() {
		return collecttime;
	}

	public void setCollecttime(Long collecttime) {
		this.collecttime = collecttime;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}