package com.monitor.device.web.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import com.monitor.common.vo.DataPointsVo;

public abstract class MessageReceiverService {

	public MessageReceiverService() {
		dataType = 1;
	}

	@Value("#{configProperties['device.datainsert.url']}")
	private String dataInsertUrl;

	/**
	 * 数据类型
	 */
	private int dataType;

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public void process(String message) {
		System.out.println(message);
		String[] datas = message.split("-");
		RestTemplate restTemplate = new RestTemplate();
		DataPointsVo vo = new DataPointsVo();
		vo.setDataType(dataType);
		vo.setDeviceSn(datas[0]);
		vo.setValue(datas[1]);
		vo.setCollectTime(new Date());
		String res = restTemplate
				.postForObject(dataInsertUrl, vo, String.class);
		System.out.println(res);
	}
}
