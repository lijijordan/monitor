package com.monitor.device.common.vo;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

@ApiModel(value = "接口返回相应")
public class ResponseVo<T> {

	private String status;
	private String message;
	private T content;

	public ResponseVo() {
		this.message = "";
	}

	@ApiModelProperty(value = "状态,200为成功，其他未失败")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ApiModelProperty(value = "返回消息，当失败时为失败原因具体描述")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@ApiModelProperty(value = "接口返回内容，返回格式为json")
	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}
}
