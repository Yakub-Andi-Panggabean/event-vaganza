package com.special.gift.app.response;

import java.io.Serializable;

public class Response implements Serializable {

  /**
   *
   */
  private static final long serialVersionUID = -1922948323228679634L;
  protected String code;
  protected String message;

  public Response() {
    super();
    // TODO Auto-generated constructor stub
  }

  public Response(String code, String message) {
    super();
    this.code = code;
    this.message = message;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Response [code=" + code + ", message=" + message + "]";
  }



}
