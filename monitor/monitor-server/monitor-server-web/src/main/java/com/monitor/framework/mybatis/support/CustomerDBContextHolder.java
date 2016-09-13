package com.monitor.framework.mybatis.support;

public class CustomerDBContextHolder {

	public final static String SESSION_FACTORY_BASE = "dataSource_base";
	public final static String SESSION_FACTORY_DEV = "dataSource_dev";

	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();

	public static void setDBContextType(String contextType) {
		contextHolder.set(contextType);
	}

	public static String getDBContextType() {
		return contextHolder.get();
	}

	public static void clearDBContextType() {
		contextHolder.remove();
	}
}
