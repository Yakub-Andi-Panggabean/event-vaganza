package com.special.gift.app.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.special.gift.app.util.CommonUtil;
import com.special.gift.app.util.HttpUtil;

@Component
public class ApplicationStartupListener implements ApplicationListener<ContextRefreshedEvent> {

  public static final Logger log = LoggerFactory.getLogger(ApplicationStartupListener.class);


  @Value("${apiurl}")
  private String apiUrl;

  @Value("${imageRequestTimeout}")
  private int imageRequestTimeout;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {

    log.info("application refreshed");
    HttpUtil.API_DOMAIN = apiUrl;
    CommonUtil.IMAGE_REQUEST_TIMEOUT = imageRequestTimeout;

    log.info("loaded api domain : {}", HttpUtil.API_DOMAIN);


  }

}
