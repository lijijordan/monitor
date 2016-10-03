package com.monitor.server.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monitor.common.define.ResponseEnum;
import com.monitor.common.vo.ResponseVo;
import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.ConstantObject;
import com.monitor.server.entity.NetworkInfo;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.web.service.UserEquService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

@Api(value = "User", description = "User Controller", hidden = false)
@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserEquService userEquService;

	@ApiOperation(value = "注册用户", httpMethod = "GET", notes = "保存用户信息")
	@RequestMapping(value = "/registerUser", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<UserInfo> registerUser(
			@ApiParam(required = true, name = "userInfo", value = "用户信息实体") @ModelAttribute("userInfo") UserInfo userInfo) {

		ResponseVo<UserInfo> responseVo = new ResponseVo<UserInfo>();
		responseVo.setStatus(ConstantObject.RETURN_TO_UI_STATAUS_FAILURE);
		responseVo.setMessage(ConstantObject.RETURN_TO_UI_STATAUS_FAILURE_MESS);

		try {
			UserInfo returnUserInfo = userEquService.registerUser(userInfo);

			responseVo.setStatus(ConstantObject.RETURN_TO_UI_STATAUS_SUCCESS);
			responseVo.setMessage(ConstantObject.RETURN_TO_UI_STATAUS_SUCCESS_MESS);
			responseVo.setContent(returnUserInfo);

		} catch (BusinessException e) {
			responseVo.setMessage(e.getMessage());
			responseVo.setContent(userInfo);
		}

		return responseVo;
	}

	@ApiOperation(value = "注册网络", httpMethod = "GET", notes = "保存用户WIFI信息")
	@RequestMapping(value = "/registerNetwork", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<NetworkInfo> registerNetwork(
			@ApiParam(required = true, name = "networkInfo", value = "网络信息实体") @ModelAttribute("networkInfo") NetworkInfo networkInfo) {

		ResponseVo<NetworkInfo> responseVo = new ResponseVo<NetworkInfo>();
		responseVo.setStatus(ConstantObject.RETURN_TO_UI_STATAUS_FAILURE);
		responseVo.setMessage(ConstantObject.RETURN_TO_UI_STATAUS_FAILURE_MESS);

		try {
			NetworkInfo returnNetworkInfo = userEquService.registerNetwork(networkInfo);

			responseVo.setStatus(ConstantObject.RETURN_TO_UI_STATAUS_SUCCESS);
			responseVo.setMessage(ConstantObject.RETURN_TO_UI_STATAUS_SUCCESS_MESS);
			responseVo.setContent(returnNetworkInfo);

		} catch (BusinessException e) {
			responseVo.setMessage(e.getMessage());
			responseVo.setContent(networkInfo);
		}

		return responseVo;
	}

	@ApiOperation(value = "绑定设备与用户关系", httpMethod = "GET", notes = "绑定设备与用户关系，保存设备与用户关联信息")
	@RequestMapping(value = "/createUserDevLink/{userAccount}/{devSN}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<UserDevInfo> createUserDevLink(
			@ApiParam(required = true, name = "userAccount", value = "用户账号") @PathVariable("userAccount") String userAccount,
			@ApiParam(required = true, name = "devSN", value = "设备SN") @PathVariable("devSN") String devSN) {

		UserDevInfo userDevInfo = new UserDevInfo();
		userDevInfo.setAccount(userAccount);
		userDevInfo.setSn(devSN);

		int numberOfRows = userEquService.createUserDevLink(userDevInfo);

		ResponseVo<UserDevInfo> responseVo = new ResponseVo<UserDevInfo>();
		if (numberOfRows == 1) {
			responseVo.setStatus(ResponseEnum.SUCCESS.getStatus());
			responseVo.setMessage(ResponseEnum.SUCCESS.getMessage());
			responseVo.setContent(userDevInfo);
		}

		// 错误情况处理待增加

		return responseVo;
	}

	@ApiOperation(value = "用户登录", httpMethod = "GET", notes = "校验账号密码")
	@RequestMapping(value = "/login/{account}/{password}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<UserInfo> login(
			@ApiParam(required = true, name = "account", value = "账号") @PathVariable("account") String account,
			@ApiParam(required = true, name = "password", value = "密码") @PathVariable("password") String password) {

		UserInfo userInfo = userEquService.selectUserByAccount(account);
		String userPass = userInfo.getPassword();

		// 用户密码加密以及解密待增加

		ResponseVo<UserInfo> responseVo = new ResponseVo<UserInfo>();
		if (password.equals(userPass)) {
			responseVo.setStatus(ResponseEnum.SUCCESS.getStatus());
			responseVo.setMessage(ResponseEnum.SUCCESS.getMessage());
			responseVo.setContent(userInfo);
		}

		// 错误情况处理待增加

		return responseVo;
	}

	@ApiOperation(value = "根据账号查询设备列表", httpMethod = "GET", notes = "根据账号，查询该用户所有设备列表")
	@RequestMapping(value = "/getDevListByUser/{account}", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseVo<String> getDevListByUser(
			@ApiParam(required = true, name = "account", value = "账号") @PathVariable("account") String account) {

		String devSN = userEquService.getDevSNByUserAccount(account);

		ResponseVo<String> responseVo = new ResponseVo<String>();
		responseVo.setStatus(ResponseEnum.SUCCESS.getStatus());
		responseVo.setMessage(ResponseEnum.SUCCESS.getMessage());
		responseVo.setContent(devSN);

		// 错误情况处理待增加

		return responseVo;

	}

}
