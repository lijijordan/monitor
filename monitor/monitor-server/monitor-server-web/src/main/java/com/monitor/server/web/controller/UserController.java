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

@Api(value = "User", description = "User Controller", hidden = false)
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@ApiOperation(value = "用户注册", httpMethod = "GET", notes = "保存用户信息")
	@RequestMapping(value = "/registerUser/{userID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsInfo> registerUser(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID) {

		return null;
	}

	@ApiOperation(value = "用户登录", httpMethod = "GET", notes = "用户登录系统，校验用户名密码")
	@RequestMapping(value = "/userLogin/{userID}/{password}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsInfo> userLogin(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID,
			@ApiParam(required = true, name = "password", value = "用户密码") @PathVariable("password") String password) {

		return null;
	}

	@ApiOperation(value = "根据用户ID，查询该设备列表", httpMethod = "GET", notes = "根据用户ID，查询该用户所有设备列表")
	@RequestMapping(value = "/getUserEquList/{userID}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<DataPointsInfo> getUserEquList(
			@ApiParam(required = true, name = "userID", value = "用户ID") @PathVariable("userID") String userID) {

		return null;
	}

}
