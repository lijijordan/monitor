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

	// 错误代码
	private Integer errorCode;

	public BusinessException(Integer errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public BusinessException(Integer errorCode, Throwable cause) {
		this(errorCode, null, cause);
	}

	public BusinessException(Integer errorCode, String errorMessage, Throwable cause) {
		super(errorMessage, cause);
		this.errorCode = errorCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String toString() {
		String errorCode = getErrorCode().toString();
		String message = getLocalizedMessage();
		return (message != null) ? (errorCode + ": " + message) : errorCode;
	}

}