package com.special.gift.app.util.response;

public class GenericResponse {

  private boolean success;
  private String message;

  public GenericResponse() {
    super();
    // TODO Auto-generated constructor stub
  }

  public GenericResponse(boolean success, String message) {
    super();
    this.success = success;
    this.message = message;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }



}
