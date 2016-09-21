package com.monitor.device.web.controller;

import javax.annotation.Resource;

import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/command")
public class CommandController {

	public CommandController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private MqttPahoMessageHandler mqtt;

	@RequestMapping(value = "/sendtest")
	public void sendMessage() {
		Message<String> message = MessageBuilder.withPayload(new String("30"))
				.setHeader(MqttHeaders.TOPIC, "PH").build();
		mqtt.handleMessage(message);

		System.out.println("成功");
	}

}
