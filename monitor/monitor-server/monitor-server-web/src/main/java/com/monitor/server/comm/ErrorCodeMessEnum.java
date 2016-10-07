package com.monitor.server.comm;

public enum ErrorCodeMessEnum {

	SUCCESS(0, "Success"), FAILURE(1, "Failure"), DatabaseError(2, "Database error."), AccountExisted(11,
			"User account has bean used."), AccountInfoError(12, "User information error."), AccountNotExisted(13,
					"User account does not existed."), AccountPasswordError(14,
							"User password error."), NetworkInfoError(21, "Network information error."), DevBinded(31,
									"Dev has bean binded."), DevAppError(41, "Dev application error.");

	private Integer errorCode;
	private String errorMessage;

	private ErrorCodeMessEnum(Integer errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public static String getErrorMessage(String errorCode) {
		for (ErrorCodeMessEnum g : ErrorCodeMessEnum.values()) {
			if (g.getErrorCode().toString().equalsIgnoreCase(errorCode)) {
				return g.errorMessage;
			}
		}
		return null;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
