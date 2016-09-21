package com.monitor.server.web.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.model.DataPointsInfo;
import com.monitor.common.vo.ResponseVo;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api(value = "Equipment", description = "Equipment Controller", hidden = false)
@Controller
@RequestMapping(value = "/equipment")
public class EquipmentController {

	@ApiOperation(value = "设备注册", httpMethod = "GET", notes = "建立用户和设备关系，保存设备信息以及与用户的关联信息")
	@RequestMapping(value = "/registerEquipment/{userID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsInfo> registerEquipment(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID) {

		return null;
	}

}
