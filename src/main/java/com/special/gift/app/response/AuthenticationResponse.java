package com.special.gift.app.response;

public class AuthenticationResponse extends Response {

  /**
   *
   */
  private static final long serialVersionUID = -47872274532104871L;
  private final String url;
  private final String username;

  public AuthenticationResponse(String url, String username, String code, String message) {
    super(code, message);
    this.url = url;
    this.username = username;
  }

  public String getUrl() {
    return url;
  }

  public String getUsername() {
    return username;
  }

  @Override
  public String toString() {
    return "AuthenticationResponse [url=" + url + ", username=" + username + ", code=" + code
        + ", message=" + message + "]";
  }



}
