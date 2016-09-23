package com.monitor.device.web.common;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 允许数据查询接口被跨域访问.
 *  ClassName: CorsConfigurerAdapter * 
 * @author 赵金义
 * @date 2016年9月23日
 * @version V1.0
 */
public class CorsConfigurerAdapter extends WebMvcConfigurerAdapter {

	@Override
	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/datafacade/*").allowedOrigins("*");
	}
}