package com.special.gift.app.util.exception;

import java.util.Map;

public class DataAlreadyExistException extends RuntimeException {

  /**
   *
   */

  private final Map<String, String> existEntity;

  private static final long serialVersionUID = 1L;

  public DataAlreadyExistException(String message, Map<String, String> field) {
    super(message);
    existEntity = field;
  }

  public Map<String, String> getExistEntity() {
    return existEntity;
  }

}
