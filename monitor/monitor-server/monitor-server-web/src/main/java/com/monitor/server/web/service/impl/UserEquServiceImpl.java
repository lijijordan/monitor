/**
 * 用户设备逻辑层
 * 
 * 目前按照一个用户只用一套设备，一个家里只有一套设备设计代码
 */
package com.monitor.server.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monitor.server.comm.OptObject;
import com.monitor.server.comm.OptResult;
import com.monitor.server.comm.OptType;
import com.monitor.server.comm.Util;
import com.monitor.server.dao.EquInfoDao;
import com.monitor.server.dao.OptInfoDao;
import com.monitor.server.dao.UserInfoDao;
import com.monitor.server.entity.EquInfo;
import com.monitor.server.entity.OptInfo;
import com.monitor.server.entity.UserInfo;
import com.monitor.server.web.service.UserEquService;

/**
 * @author yinhong
 *
 */
@Service("userEquService")
public class UserEquServiceImpl implements UserEquService {

	@Autowired
	private UserInfoDao userInfoDao;

	@Autowired
	private EquInfoDao equInfoDao;

	@Autowired
	private OptInfoDao optInfoDao;

	/**
	 * 发现设备
	 */
	@Override
	public EquInfo discoverEqu() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 注册用户和设备
	 */
	@Override
	@Transactional
	public void regUserEqu(UserInfo userInfo, EquInfo equInfo) {

		// 校验输入条件
		checkUserIsExisted(userInfo.getName());
		checkUserInfoIsComplete(userInfo);
		checkEquIsExisted(equInfo.getSn());
		checkEquInfoIsComplete(equInfo);

		// 保存用户信息
		createUser(userInfo);

		// 保存设备信息
		equInfo.setUserInfo(userInfo);
		createEqu(equInfo);

		// 记录用户操作记录
		OptInfo optInfo = Util.createOptInfo(OptType.CREATE.getValue(), OptObject.USEREQU.getValue(),
				OptResult.SUCCESS.getValue(), userInfo, equInfo);
		createOptInfo(optInfo);

		/**
		 * 跳转到注册成功页面，注册成功页面跳转到首页
		 */
	}

	/**
	 * 用户登录
	 */
	@Override
	public void login(UserInfo userInfo) {

		/**
		 * 1、通过用户名查询用户和设备信息（查询失败，跳转到密码错误或网络异常页面）
		 * 2、通过设备信息连接网络（连接网络失败，跳转到修改网络SSID和密码页面） 3、跳转到首页
		 */

	}

	/**
	 * 检查用户名是否已经被注册
	 */
	public void checkUserIsExisted(String userName) {
		// TODO Auto-generated method stub

	}

	/**
	 * 检查用户信息是否完整（比填项是否全面填写、校验用户信息是否符合规则）
	 * 
	 * @param userInfo
	 */
	private void checkUserInfoIsComplete(UserInfo userInfo) {

	}

	/**
	 * 检查设备是否已经被注册
	 */
	@Override
	public void checkEquIsExisted(String equSN) {
		// TODO Auto-generated method stub

	}

	/**
	 * 检查设备信息是否完整（比填项是否全面填写、校验用户信息是否符合规则）
	 * 
	 * @param userInfo
	 */
	private void checkEquInfoIsComplete(EquInfo equInfo) {

	}

	/**
	 * 注销用户
	 */
	@Override
	public void logout(UserInfo userInfo) {
		// TODO Auto-generated method stub

	}

	/**
	 * 修改用户信息
	 */
	@Override
	public void editUserInfo(UserInfo userInfo) {
		// TODO Auto-generated method stub

	}

	/**
	 * 修改设备信息（SSID、密码）
	 */
	@Override
	public void editEquInfo(EquInfo equInfo) {
		// TODO Auto-generated method stub

	}

	@Override
	public int createUser(UserInfo userInfo) {
		return userInfoDao.createUser(userInfo);
	}

	@Override
	public int updateUser(UserInfo userInfo) {
		return userInfoDao.updateUser(userInfo);
	}

	@Override
	public int deleteUser(int userID) {
		return userInfoDao.deleteUser(userID);
	}

	@Override
	public List<UserInfo> selectAllUsers() {
		return userInfoDao.selectAllUser();
	}

	@Override
	public int countAllUser() {
		return userInfoDao.countAllUserNum();
	}

	@Override
	public UserInfo findUserByID(int userID) {
		return userInfoDao.selectUserById(userID);
	}

	@Override
	public int createEqu(EquInfo equInfo) {
		return equInfoDao.createEqu(equInfo);
	}

	@Override
	public int updateEqu(EquInfo equInfo) {
		return equInfoDao.updateEqu(equInfo);
	}

	@Override
	public int deleteEqu(int equID) {
		return equInfoDao.delete(equID);
	}

	@Override
	public List<EquInfo> selectAllEqus() {
		return equInfoDao.selectAllEqus();
	}

	@Override
	public int countAllEqu() {
		return equInfoDao.countAllEquNum();
	}

	@Override
	public EquInfo findEquByID(int equID) {
		return equInfoDao.selectEquById(equID);
	}

	@Override
	public int createOptInfo(OptInfo optInfo) {
		return optInfoDao.createOptInfo(optInfo);
	}

}
