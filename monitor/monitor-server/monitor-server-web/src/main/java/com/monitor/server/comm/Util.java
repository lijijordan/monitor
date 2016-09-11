/**
 * 工具类
 */
package com.monitor.server.comm;

import com.monitor.server.entity.EquInfo;
import com.monitor.server.entity.OptInfo;
import com.monitor.server.entity.UserInfo;

public final class Util {

	public static OptInfo createOptInfo(int optType, int optObject, int result, UserInfo userInfo, EquInfo equInfo) {
		OptInfo optInfo = new OptInfo();

		optInfo.setEquInfo(equInfo);
		optInfo.setOptObject(optObject);
		optInfo.setOptType(optType);
		optInfo.setResult(result);
		optInfo.setUserInfo(userInfo);

		return optInfo;
	}

}
