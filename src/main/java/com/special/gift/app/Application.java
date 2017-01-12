package com.special.gift.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.special.gift.app.config.MvcConfig;

@SpringBootApplication
@Import(value = {MvcConfig.class})
public class Application extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
    return builder.sources(Application.class);
  }

  public static void main(String[] args) {
    try {
      SpringApplication.run(Application.class, args);
    } catch (final Exception exception) {
      exception.printStackTrace();
    }
  }
}
