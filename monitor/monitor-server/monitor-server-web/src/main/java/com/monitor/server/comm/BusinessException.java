/**
 * 定义业务异常
 */
package com.monitor.server.comm;

/**
 * @author yinhong
 *
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -5050745931951542954L;

	public BusinessException(String errorMessage) {
		super(errorMessage);
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	public BusinessException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}

}