package com.monitor.server.entity.dev;

import java.io.Serializable;

/**
 * 从设备层获取历史传感器值
 * 
 * @author yinhong
 *
 */
public class DataPointsDevStatisticsInfo implements Serializable {

	private static final long serialVersionUID = -1909449829163487238L;

	private Long id;

	private Integer statisticstype;

	private Long sensorid;

	private Long collecttime;

	private String value;

	private String maxvalue;

	private String minvalue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatisticstype() {
		return statisticstype;
	}

	public void setStatisticstype(Integer statisticstype) {
		this.statisticstype = statisticstype;
	}

	public Long getSensorid() {
		return sensorid;
	}

	public void setSensorid(Long sensorid) {
		this.sensorid = sensorid;
	}

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
		this.value = value == null ? null : value.trim();
	}

	public String getMaxvalue() {
		return maxvalue;
	}

	public void setMaxvalue(String maxvalue) {
		this.maxvalue = maxvalue == null ? null : maxvalue.trim();
	}

	public String getMinvalue() {
		return minvalue;
	}

	public void setMinvalue(String minvalue) {
		this.minvalue = minvalue == null ? null : minvalue.trim();
	}
}