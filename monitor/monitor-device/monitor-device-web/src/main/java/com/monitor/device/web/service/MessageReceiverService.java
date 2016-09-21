package com.monitor.device.web.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.client.RestTemplate;

import com.monitor.common.vo.DataPointsVo;

public class MessageReceiverService {

	public MessageReceiverService() {
		// TODO Auto-generated constructor stub
	}

	public void startCase(String message) {
		System.out.println(message);
		String msg = message;
		RestTemplate restTemplate = new RestTemplate();
		DataPointsVo vo = new DataPointsVo();
		vo.setDataType(2);
		vo.setDeviceSn("fisher01");
		vo.setValue("35");
		SimpleDateFormat sdf = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.sss'Z'");
		Date dtNow = sdf.getCalendar().getTime();
		vo.setCollectTime(dtNow);
		String res = restTemplate.postForObject(
				"http://106.185.35.79:9099/monitor-device-web/dataPoints/add",
				vo, String.class);
		System.out.println(res);
	}
}
