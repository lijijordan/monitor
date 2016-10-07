package com.monitor.server.web.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ErrorCodeMessEnum;
import com.monitor.server.entity.FishTankInfo;
import com.monitor.server.entity.NetworkInfo;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.web.service.UserEquService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api(value = "User", description = "User Controller")
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserEquService userEquService;

	public static Logger userControllerLogger = Logger.getLogger(UserController.class);

	@ApiOperation(value = "注册用户", httpMethod = "POST", notes = "保存用户信息")
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<UserInfo> registerUser(
			@ApiParam(required = true, name = "userInfo", value = "用户信息实体") @RequestBody UserInfo userInfo) {

		ResponseVo<UserInfo> responseVo = new ResponseVo<UserInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		try {
			UserInfo returnUserInfo = userEquService.registerUser(userInfo);

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(returnUserInfo);

		} catch (BusinessException e) {
			userControllerLogger.error(e.getMessage(), e);

			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

	@ApiOperation(value = "编辑用户", httpMethod = "POST", notes = "修改用户信息")
	@RequestMapping(value = "/editUser", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<?> editUser(
			@ApiParam(required = true, name = "userInfo", value = "用户信息实体") @RequestBody UserInfo userInfo,
			@ApiParam(required = true, name = "fishTackInfo", value = "鱼缸信息实体") @RequestBody FishTankInfo fishTankInfo) {

		ResponseVo<UserInfo> responseVo = new ResponseVo<UserInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		try {
			userEquService.editUserInfo(userInfo, fishTankInfo);

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());

		} catch (BusinessException e) {
			userControllerLogger.error(e.getMessage(), e);

			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

	@ApiOperation(value = "注册网络", httpMethod = "POST", notes = "保存用户WIFI信息")
	@RequestMapping(value = "/registerNetwork", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<NetworkInfo> registerNetwork(
			@ApiParam(required = true, name = "networkInfo", value = "网络信息实体") @RequestBody NetworkInfo networkInfo) {

		ResponseVo<NetworkInfo> responseVo = new ResponseVo<NetworkInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		try {
			NetworkInfo returnNetworkInfo = userEquService.registerNetwork(networkInfo);

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(returnNetworkInfo);

		} catch (BusinessException e) {
			userControllerLogger.error(e.getMessage(), e);

			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

	@ApiOperation(value = "绑定设备与用户关系", httpMethod = "GET", notes = "绑定设备与用户关系，保存设备与用户关联信息")
	@RequestMapping(value = "/bindUserDev/{userAccount}/{devSN}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<UserDevInfo> bindUserDev(
			@ApiParam(required = true, name = "userAccount", value = "用户账号") @PathVariable("userAccount") String userAccount,
			@ApiParam(required = true, name = "devSN", value = "设备SN") @PathVariable("devSN") String devSN) {

		UserDevInfo userDevInfo = new UserDevInfo();
		userDevInfo.setUserAccount(userAccount);
		userDevInfo.setDevSN(devSN);

		ResponseVo<UserDevInfo> responseVo = new ResponseVo<UserDevInfo>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		try {
			UserDevInfo returnUserDevInfo = userEquService.bindUserDev(userDevInfo);

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(returnUserDevInfo);

		} catch (BusinessException e) {
			userControllerLogger.error(e.getMessage(), e);

			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

	@ApiOperation(value = "用户登录", httpMethod = "GET", notes = "校验账号密码，返回用户绑定的设备SN")
	@RequestMapping(value = "/login/{userAccount}/{password}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<String> login(
			@ApiParam(required = true, name = "userAccount", value = "用户账号") @PathVariable("userAccount") String userAccount,
			@ApiParam(required = true, name = "password", value = "用户密码") @PathVariable("password") String password) {

		ResponseVo<String> responseVo = new ResponseVo<String>();
		responseVo.setStatus(ErrorCodeMessEnum.FAILURE.getErrorCode().toString());
		responseVo.setMessage(ErrorCodeMessEnum.FAILURE.getErrorMessage());

		String devSN = null;

		try {
			devSN = userEquService.login(userAccount, password);

			responseVo.setStatus(ErrorCodeMessEnum.SUCCESS.getErrorCode().toString());
			responseVo.setMessage(ErrorCodeMessEnum.SUCCESS.getErrorMessage());
			responseVo.setContent(devSN);

		} catch (BusinessException e) {
			userControllerLogger.error(e.getMessage(), e);

			responseVo.setMessage(e.getMessage());
		}

		return responseVo;
	}

}
