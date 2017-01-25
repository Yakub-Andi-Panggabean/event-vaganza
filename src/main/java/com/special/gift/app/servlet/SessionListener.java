package com.special.gift.app.servlet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SessionListener implements HttpSessionListener {

  private static final Logger log = LoggerFactory.getLogger(SessionListener.class);

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    log.debug("session created");
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    log.debug("session destroyed");
  }

}
