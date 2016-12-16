/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信服务端入口
 * @author: yinhong
 * @date: 2016年11月25日 下午6:20:37
 * @version: V1.0
 */
package com.monitor.server.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.PropertiesUtil;
import com.monitor.server.comm.wx.WxCommUtil;
import com.monitor.server.comm.wx.WxMsgType;
import com.monitor.server.comm.wx.WxMsgUtil;
import com.monitor.server.comm.wx.WxOAuthToken;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.service.UserDevService;
import com.monitor.server.service.UserService;
import com.monitor.server.service.dispatcher.WxEventDispatcher;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

/**
 * @Description: 微信服务端入口
 */
@Api(value = "WxEntrance", description = "WxEntrance Controller")
@Controller
@RequestMapping("/wxentrance")
public class WxEntranceController {

  private static final Logger logger = LoggerFactory.getLogger(WxEntranceController.class);

  @Autowired
  private UserService userService;
  @Autowired
  private UserDevService userDevService;

  @ApiOperation(value = "微信服务器验证")
  @SuppressWarnings("deprecation")
  @RequestMapping(value = "getin", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
  public void wxServerVerify(HttpServletRequest request, HttpServletResponse response,
      @RequestParam(value = "signature", required = true) String signature,
      @RequestParam(value = "timestamp", required = true) String timestamp,
      @RequestParam(value = "nonce", required = true) String nonce,
      @RequestParam(value = "echostr", required = true) String echostr)
      throws UnsupportedEncodingException {

    PrintWriter out = null;
    String result = "";

    try {
      out = response.getWriter();
      String[] str = {PropertiesUtil.getProperty("webchat.token"), timestamp, nonce};
      Arrays.sort(str);
      String bigStr = str[0] + str[1] + str[2];
      String digest = DigestUtils.shaHex(bigStr.getBytes());
      // 确认请求来至微信服务器验证并验证成功
      if (digest.equals(signature)) {
        result = echostr;
      }
      out.print(result);
    } catch (IOException e) {
      logger.error("verify server failed.", e);
    } finally {
      if (out != null) {
        out.close();
      }
    }

  }

  @ApiOperation(value = "执行微信公众号事件以及消息处理")
  @RequestMapping(value = "getin", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
  public void wxProcedure(HttpServletRequest request, HttpServletResponse response)
      throws UnsupportedEncodingException {

    PrintWriter out = null;
    String result = "";
    try {
      out = response.getWriter();
      // 解析微信请求的XML数据
      Map<String, String> map = WxMsgUtil.parseXml(request);
      String msgtype = map.get("MsgType");
      // 根据MsgType类型判断处理方式, 进入事件或消息处理
      if (WxMsgType.REQ_MESSAGE_TYPE_EVENT.equals(msgtype)) {
        result = WxEventDispatcher.processEvent(map);
      }
      out.print(result);
    } catch (IOException | DocumentException | URISyntaxException e) {
      logger.error("handle webchat message or event failed.", e);
    } finally {
      if (out != null) {
        out.close();
      }
    }

  }

  @ApiOperation(value = "页面鉴权以及获取用户openid,通过openid从数据库获取用户基本及信息")
  @RequestMapping(value = "oauth", method = RequestMethod.GET, consumes = MediaType.ALL_VALUE)
  public void oAuth2(HttpServletRequest request, HttpServletResponse response,
      @RequestParam(value = "code", required = true) String code)
      throws ServletException, IOException {

    String appId = PropertiesUtil.getProperty("webchat.appid");
    String appSecret = PropertiesUtil.getProperty("webchat.appsecret");
    String paramsUrl = "";

    if (!"authdeny".equals(code)) {
      try {
        // 获取网页授权access_token
        WxOAuthToken wxOAuth2Token = WxCommUtil.getOauth2Token(appId, appSecret, code);
        // 用户标识
        String openId = wxOAuth2Token.getOpenId();
        // 根据用户OpenID，从数据库获取用户和设备信息
        UserInfo userInfo = userService.getUserByAccount(openId);
        UserDevInfo userDevInfo = userDevService.selectByUserAccount(openId);
        paramsUrl = "?openid=" + userInfo.getAccount() + "&nickname=" + userInfo.getNickName()
            + "&devsn=" + userDevInfo.getDevSn();
        // 鉴权完成，页面跳转到首页
        response.sendRedirect(PropertiesUtil.getProperty("ui.index.url") + paramsUrl);
        return;
      } catch (IOException | KeyManagementException | NoSuchAlgorithmException
          | NoSuchProviderException | BusinessException e) {
        logger.error("oauth2 failed.", e);
      }
    }

    // 如果发生错误，跳转到错误界面
    response.sendRedirect(PropertiesUtil.getProperty("ui.error.url"));
  }

}
