/**
 * Copyright © 2016 Seven Color. All rights reserved.
 *
 * @Description: 微信事件派发器
 * @author: yinhong
 * @date: 2016年11月29日 下午1:44:33
 * @version: V1.0
 */
package com.monitor.server.service.dispatcher;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.monitor.server.comm.BusinessException;
import com.monitor.server.comm.wx.WxCommUtil;
import com.monitor.server.comm.wx.WxMsgType;
import com.monitor.server.comm.wx.WxMsgUtil;
import com.monitor.server.entity.UserDevInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.entity.wx.msg.WxArticle;
import com.monitor.server.entity.wx.resp.WxRespNewsMsg;
import com.monitor.server.entity.wx.resp.WxRespTextMsg;
import com.monitor.server.service.UserDevService;
import com.monitor.server.service.UserService;

/**
 * @Description: 微信事件派发器，根据不同的事件类型进行处理
 */
@Component
public class WxEventDispatcher {

  private static final Logger logger = LoggerFactory.getLogger(WxEventDispatcher.class);

  @Autowired
  private UserService userService;
  @Autowired
  private UserDevService userDevService;

  // 静态方法调用容器Bean的使用方法
  private static WxEventDispatcher webchatEventDispatcher;

  /**
   * @Description: 静态方法调用容器Bean的使用方法
   * @return: void
   */
  @PostConstruct
  public void init() {
    webchatEventDispatcher = this;
    webchatEventDispatcher.userService = this.userService;
    webchatEventDispatcher.userDevService = this.userDevService;
  }

  public static String processEvent(Map<String, String> map)
      throws ClientProtocolException, URISyntaxException, IOException {

    String openid = map.get("FromUserName");
    String mpid = map.get("ToUserName");
    String eventType = map.get("Event");
    String resultStr = "";

    switch (eventType) {

      // 关注事件
      case WxMsgType.EVENT_TYPE_SUBSCRIBE: {

        // 保存关注用户基本信息到数据库
        UserInfo wxUserInfo = WxCommUtil.getUserInfoForWxClient(openid);
        try {
          webchatEventDispatcher.userService.addUser(wxUserInfo);
        } catch (BusinessException e) {
          logger.error("WX subscribe add user error.", e);
        }

        // 提示信息，图文消息
        WxRespNewsMsg newmsg = new WxRespNewsMsg();
        newmsg.setToUserName(openid);
        newmsg.setFromUserName(mpid);
        newmsg.setCreateTime(new Date().getTime());
        newmsg.setMsgType(WxMsgType.RESP_MESSAGE_TYPE_NEWS);

        WxArticle article = new WxArticle();
        article.setDescription("Wellcome to seven color world：");
        article.setPicUrl(wxUserInfo.getHeadimgurl());
        // article.setTitle("尊敬的：" + wxUserInfo.getNickName() + ",你好！");
        // article.setUrl("http://www.baidu.com");
        List<WxArticle> list = new ArrayList<WxArticle>();
        list.add(article);
        newmsg.setArticleCount(list.size());
        newmsg.setArticles(list);
        resultStr = WxMsgUtil.newsMessageToXml(newmsg);

        break;
      }

      // 取消关注事件
      case WxMsgType.EVENT_TYPE_UNSUBSCRIBE: {

        // 删除用户
        try {
          webchatEventDispatcher.userService.deleteUser(openid);
        } catch (BusinessException e) {
          logger.error("unsubscribe error", e);
        }

        break;
      }

      // 扫描二维码推送事件
      case WxMsgType.EVENT_TYPE_SCAN_WAITMSG: {

        // 设置用户与设备关系对象
        UserDevInfo userDevInfo = new UserDevInfo();
        userDevInfo.setDevSn(map.get("ScanResult"));
        userDevInfo.setUserAccount(openid);

        // 保存数据库
        try {
          webchatEventDispatcher.userDevService.bindUserDev(userDevInfo);
        } catch (BusinessException e) {
          logger.error("scan error.", e);
        }

        // 提示信息
        WxRespTextMsg txtmsg = new WxRespTextMsg();
        txtmsg.setToUserName(openid);
        txtmsg.setFromUserName(mpid);
        txtmsg.setCreateTime(new Date().getTime());
        txtmsg.setMsgType(WxMsgType.RESP_MESSAGE_TYPE_TEXT);
        txtmsg.setContent("用户绑定设备成功。");
        resultStr = WxMsgUtil.textMessageToXml(txtmsg);

        break;
      }

      // 位置上报
      case WxMsgType.EVENT_TYPE_LOCATION: {
        break;
      }

      // 自定义菜单点击事件
      case WxMsgType.EVENT_TYPE_CLICK: {
        break;
      }

      // 自定义View事件
      case WxMsgType.EVENT_TYPE_VIEW: {
        break;
      }

      default: {
        break;
      }
    }

    return resultStr;
  }

}
