package com.monitor.server.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.monitor.framework.mybatis.support.CustomerDBContextHolder;
import com.monitor.server.entity.PhInfo;
import com.monitor.server.web.service.SensorService;

public class Test {
	public static void main(String[] args) throws ParseException {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-ioc-config.xml");
		SensorService sensorService = (SensorService) applicationContext.getBean("sensorService");

		// UserInfo userInfo = new UserInfo();
		// userInfo.setAge(1);
		// userInfo.setAquariumsAge(5);
		// userInfo.setAquariumsHeigth(2);
		// userInfo.setAquariumsLength(1);
		// userInfo.setAquariumsType(1);
		// userInfo.setAquariumsWidth(3);
		// userInfo.setEmail("yinhong2015@outlook.com");
		// userInfo.setName("123456");
		// userInfo.setPwd("111111");
		// userInfo.setSex(1);
		// userInfo.setTelNum("18628250959");
		// userInfo.setWebChat("webchatnum");
		//
		// EquInfo equInfo = new EquInfo();
		// equInfo.setPwd("terry2008");
		// equInfo.setSn("XXXXXXXXXXXXXXXXXX");
		// equInfo.setSsid(null);

		CustomerDBContextHolder.setDBContextType(CustomerDBContextHolder.SESSION_FACTORY_DEV);

		// PhInfo phInfo = new PhInfo();
		// phInfo.setCrateTime(new Date());
		// phInfo.setEquId(1);
		// phInfo.setUserId(8);
		// phInfo.setValue(20);

		// try {
		// sensorService.createPH(phInfo);
		// } catch (BusinessException e) {
		// System.out.println(e.getMessage());
		// }

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate = dateFormat.parse("2016-07-24 15:58:05");

		HashMap hashMap = new HashMap();
		hashMap.put("userID", 8);
		hashMap.put("equID", 1);
		hashMap.put("historyTime", myDate);

		// List<PhInfo> phInfoList = sensorService.selectHistoryPH(hashMap);
		//
		// for (PhInfo phInfo : phInfoList) {
		// System.out.println(phInfo.getValue());
		// }

		// EquInfo equInfo = userEquService.findEquByID(1);
		//
		// System.out.println(equInfo.getUserInfo().getAge());

		// CustomerDBContextHolder.setDBContextType(CustomerDBContextHolder.SESSION_FACTORY_DEV);
		// PhInfoDao phInfoDao = (PhInfoDao)
		// applicationContext.getBean("PhInfoDao");
		// PhInfo phInfo = phInfoDao.findPhInfoById("12");
		// System.out.println(phInfo.getValue());
	}
}
