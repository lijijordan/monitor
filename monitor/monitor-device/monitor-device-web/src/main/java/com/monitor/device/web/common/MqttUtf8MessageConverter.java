package com.monitor.device.web.common;

import java.nio.charset.StandardCharsets;

import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.integration.mqtt.support.MqttMessageConverter;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.DefaultMessageBuilderFactory;
import org.springframework.integration.support.MessageBuilderFactory;
import org.springframework.integration.support.utils.IntegrationUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.converter.MessageConversionException;
import org.springframework.util.Assert;

public class MqttUtf8MessageConverter implements MqttMessageConverter {

	private String charset = "UTF-8";

	private final Integer defaultQos;

	private final Boolean defaultRetained;

	private volatile boolean payloadAsBytes = false;

	private volatile BeanFactory beanFactory;

	private volatile MessageBuilderFactory messageBuilderFactory = new DefaultMessageBuilderFactory();

	public MqttUtf8MessageConverter() {
		this(0, false);
	}

	public MqttUtf8MessageConverter(int defaultQos, boolean defaultRetain) {
		this(defaultQos, defaultRetain, "UTF-8");
	}

	public final void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
		this.messageBuilderFactory = IntegrationUtils
				.getMessageBuilderFactory(this.beanFactory);
	}

	protected BeanFactory getBeanFactory() {
		return beanFactory;
	}

	protected MessageBuilderFactory getMessageBuilderFactory() {
		return messageBuilderFactory;
	}

	/**
	 * True if the converter should not convert the message payload to a String.
	 * 
	 * @param payloadAsBytes
	 *            The payloadAsBytes to set.
	 */
	public void setPayloadAsBytes(boolean payloadAsBytes) {
		this.payloadAsBytes = payloadAsBytes;
	}

	public boolean isPayloadAsBytes() {
		return this.payloadAsBytes;
	}

	public MqttUtf8MessageConverter(int defaultQos, boolean defaultRetained,
			String charset) {
		this.defaultQos = defaultQos;
		this.defaultRetained = defaultRetained;
		this.charset = charset;
	}

	@Override
	public Message<?> toMessage(Object mqttMessage, MessageHeaders headers) {
		Assert.isInstanceOf(MqttMessage.class, mqttMessage);
		return toMessage(null, (MqttMessage) mqttMessage);
	}

	@Override
	public Message<?> toMessage(String topic, MqttMessage mqttMessage) {
		try {
			AbstractIntegrationMessageBuilder<Object> messageBuilder = this.messageBuilderFactory
					.withPayload(mqttBytesToPayload(mqttMessage))
					.setHeader(MqttHeaders.QOS, mqttMessage.getQos())
					.setHeader(MqttHeaders.DUPLICATE, mqttMessage.isDuplicate())
					.setHeader(MqttHeaders.RETAINED, mqttMessage.isRetained());
			if (topic != null) {
				messageBuilder.setHeader(MqttHeaders.TOPIC, topic);
			}
			return messageBuilder.build();
		} catch (Exception e) {
			throw new MessageConversionException(
					"failed to convert object to Message", e);
		}
	}

	@Override
	public MqttMessage fromMessage(Message<?> message, Class<?> targetClass) {
		byte[] payloadBytes = messageToMqttBytes(message);
		MqttMessage mqttMessage = new MqttMessage(payloadBytes);
		Object header = message.getHeaders().get(MqttHeaders.RETAINED);
		Assert.isTrue(header == null || header instanceof Boolean,
				MqttHeaders.RETAINED + " header must be Boolean");
		mqttMessage.setRetained(header == null ? this.defaultRetained
				: (Boolean) header);
		header = message.getHeaders().get(MqttHeaders.QOS);
		Assert.isTrue(header == null || header instanceof Integer,
				MqttHeaders.QOS + " header must be Integer");
		mqttMessage.setQos(header == null ? this.defaultQos : (Integer) header);
		return mqttMessage;
	}

	/**
	 * Subclasses can override this method to convert the byte[] to a payload.
	 * The default implementation creates a String (default) or byte[].
	 * 
	 * @param mqttMessage
	 *            The inbound message.
	 * @return The payload for the Spring integration message
	 * @throws Exception
	 *             Any.
	 */
	protected Object mqttBytesToPayload(MqttMessage mqttMessage)
			throws Exception {
		if (this.payloadAsBytes) {
			return mqttMessage.getPayload();
		} else {

			return new String(mqttMessage.getPayload(), StandardCharsets.UTF_8);
		}
	}

	/**
	 * Subclasses can override this method to convert the payload to a byte[].
	 * The default implementation accepts a byte[] or String payload.
	 * 
	 * @param message
	 *            The outbound Message.
	 * @return The byte[] which will become the payload of the MQTT Message.
	 */
	protected byte[] messageToMqttBytes(Message<?> message) {
		Object payload = message.getPayload();
		Assert.isTrue(payload instanceof byte[] || payload instanceof String);
		byte[] payloadBytes;
		if (payload instanceof String) {
			try {
				payloadBytes = ((String) payload).getBytes(this.charset);
			} catch (Exception e) {
				throw new MessageConversionException(
						"failed to convert Message to object", e);
			}
		} else {
			payloadBytes = (byte[]) payload;
		}
		return payloadBytes;
	}

}
