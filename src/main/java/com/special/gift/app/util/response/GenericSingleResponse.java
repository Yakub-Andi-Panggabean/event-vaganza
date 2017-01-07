package com.special.gift.app.util.response;

public class GenericSingleResponse<T> extends GenericResponse {

  private T content;

  public GenericSingleResponse() {
    super();
    // TODO Auto-generated constructor stub
  }

  public GenericSingleResponse(boolean success, String message, T content) {
    super(success, message);
    this.content = content;
    // TODO Auto-generated constructor stub
  }

  public T getContent() {
    return content;
  }

  public void setContent(T content) {
    this.content = content;
  }



}
