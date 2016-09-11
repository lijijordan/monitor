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

	public BusinessException(String frdMessage) {
		super(createFriendlyErrMsg(frdMessage));
	}

	public BusinessException(Throwable throwable) {
		super(throwable);
	}

	public BusinessException(Throwable throwable, String frdMessage) {
		super(throwable);
	}

	private static String createFriendlyErrMsg(String msgBody) {
		String prefixStr = "Sorry,";
		String suffixStr = " please contact with administrator later!";

		StringBuffer friendlyErrMsg = new StringBuffer("");

		friendlyErrMsg.append(prefixStr);

		friendlyErrMsg.append(msgBody);

		friendlyErrMsg.append(suffixStr);

		return friendlyErrMsg.toString();
	}
}