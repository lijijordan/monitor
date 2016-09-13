/**
 * 异常信息打印以及抛出异常
 */
package com.monitor.server.comm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.dao.DataAccessException;

/**
 * @author yinhong
 *
 */
public class ExceptionAdvisor implements ThrowsAdvice {

	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable {

		Logger log = Logger.getLogger(target.getClass());
		log.info("**************************************************************");
		log.info("Error happened in class: " + target.getClass().getName());
		log.info("Error happened in method: " + method.getName());
		for (int i = 0; i < args.length; i++) {
			log.info("arg[" + i + "]: " + args[i]);
		}
		log.info("Exception class: " + ex.getClass().getName());
		log.info("ex.getMessage():" + ex.getMessage());
		log.info("**************************************************************");

		// 在这里判断异常，根据不同的异常返回错误
		if (ex.getClass().equals(DataAccessException.class)) {
			throw new BusinessException("database access error.");
		} else if (ex.getClass().toString().equals(NullPointerException.class.toString())) {
			throw new BusinessException("object is null.");
		} else if (ex.getClass().equals(IOException.class)) {
			throw new BusinessException("IO error.");
		} else if (ex.getClass().equals(ClassNotFoundException.class)) {
			throw new BusinessException("class is not existed.");
		} else if (ex.getClass().equals(ArithmeticException.class)) {
			throw new BusinessException("arithmetic error.");
		} else if (ex.getClass().equals(ArrayIndexOutOfBoundsException.class)) {
			throw new BusinessException("array index out of bounds.");
		} else if (ex.getClass().equals(IllegalArgumentException.class)) {
			throw new BusinessException("argument error.");
		} else if (ex.getClass().equals(ClassCastException.class)) {
			throw new BusinessException("class cast error.");
		} else if (ex.getClass().equals(SecurityException.class)) {
			throw new BusinessException("security exception.");
		} else if (ex.getClass().equals(SQLException.class)) {
			throw new BusinessException("sql error.");
		} else if (ex.getClass().equals(NoSuchMethodError.class)) {
			throw new BusinessException("no such method.");
		} else if (ex.getClass().equals(InternalError.class)) {
			throw new BusinessException("JVM internal error.");
		} else {
			throw new BusinessException("other error." + ex.getMessage());
		}
	}
}
