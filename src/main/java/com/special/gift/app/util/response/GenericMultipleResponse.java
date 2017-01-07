package com.special.gift.app.util.response;

import org.springframework.data.domain.Page;

public class GenericMultipleResponse<T> extends GenericResponse {

  private Page<T> contents;

  public GenericMultipleResponse() {
    super();
  }

  public GenericMultipleResponse(boolean success, String message, Page<T> contents) {
    super(success, message);
    this.contents = contents;
  }

  public Page<T> getContents() {
    return contents;
  }

  public void setContents(Page<T> contents) {
    this.contents = contents;
  }



}
