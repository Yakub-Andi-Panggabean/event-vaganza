package com.special.gift.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.special.gift.app.config.MvcConfig;

@SpringBootApplication
@Import(value = {MvcConfig.class})
public class Application {

  public static void main(String[] args) {
    try {
      SpringApplication.run(Application.class, args);
    } catch (final Exception exception) {
      exception.printStackTrace();
    }
  }
}
