package com.monitor.server.comm;

public enum ErrorCodeMessEnum {

  SUCCESS(0, "Success"), 
  FAILURE(1, "Failure"), 
  DatabaseError(2, "Database error."), 
  AccountExisted(11, "User account has bean used."), 
  AccountInfoError(12, "User information error."), 
  AccountNotExisted(13, "User account does not existed."), 
  AccountPasswordError(14, "User password error."), 
  NetworkInfoError(15, "Network information error."), 
  DevBinded(16, "Dev has bean binded."), 
  DevAppError(17, "Dev application error."),
  DevNotExisted(18, "Dev does not existed."),
  ParamsIsBlank(19, "Params is blank."),
  UserDevNoBinded(20, "Dev user has not bean binded."); 
  
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
