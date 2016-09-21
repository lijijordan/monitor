package com.monitor.device.web.controller;

import javax.annotation.Resource;

import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.monitor.common.vo.CommandVo;
import com.monitor.common.vo.DataPointsVo;
import com.monitor.common.vo.ResponseVo;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Controller
@RequestMapping("/command")
public class CommandController {

	public CommandController() {
		// TODO Auto-generated constructor stub
	}

	@Resource
	private MqttPahoMessageHandler mqtt;

	@RequestMapping(value = "/sendtest", method = RequestMethod.POST)
	@ApiOperation(value = "测试发送指令，可测试数据上报", httpMethod = "POST", notes = "传入设备id，数据类型，值， 数据类型为通道名称也是枚举文本")
	public void sendMessage(@RequestBody DataPointsVo vo) {
		System.out.println(vo.toString());
		Message<String> message = MessageBuilder
				.withPayload(vo.getDeviceSn() + "-" + vo.getValue())
				.setHeader(MqttHeaders.TOPIC,
						String.valueOf(vo.getDataTypeText())).build();
		mqtt.handleMessage(message);

		System.out.println("成功");
	}

	@RequestMapping(value = "/send", method = RequestMethod.POST)
	@ApiOperation(value = "发送指令", httpMethod = "POST", notes = "向设备发送指令，设备id为通道名称， 指令文本为需要发送的指令")
	public void sendMessage(@RequestBody CommandVo vo) {
		System.out.println(vo.toString());
		Message<String> message = MessageBuilder
				.withPayload(vo.getCommandText())
				.setHeader(MqttHeaders.TOPIC, vo.getDeviceSn()).build();
		mqtt.handleMessage(message);
		System.out.println("成功");
	}
}
