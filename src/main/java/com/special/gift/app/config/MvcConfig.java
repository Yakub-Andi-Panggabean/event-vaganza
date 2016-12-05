package com.special.gift.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan({MvcConfig.CONTROLLER_PACKAGE, MvcConfig.SERVICE_PACKAGE,
    MvcConfig.REPOSITORY_PACKAGE, MvcConfig.UTIL_PACKAGE})
public class MvcConfig extends WebMvcConfigurerAdapter {

  private static final String BASE_PACKAGE = "com.special.gift.app";

  public static final String CONTROLLER_PACKAGE = BASE_PACKAGE + ".controller";
  public static final String SERVICE_PACKAGE = BASE_PACKAGE + ".service";
  public static final String REPOSITORY_PACKAGE = BASE_PACKAGE + ".repository";
  public static final String UTIL_PACKAGE = BASE_PACKAGE + ".util";

  @Autowired
  private ThymeleafViewInterceptor interceptor;

  /**
   *
   * adding interceptor for template engine
   *
   */
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(interceptor);
  }



}
