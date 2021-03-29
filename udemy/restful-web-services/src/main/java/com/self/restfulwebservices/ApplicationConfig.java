package com.self.restfulwebservices;


import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;


@Configuration
public class ApplicationConfig {

  @Bean
  public LocaleResolver localeResolver() {
//    SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
    // As we are sending "Accept-Language" in the header we can use AcceptHeaderLocaleResolver instead.
    AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
    sessionLocaleResolver.setDefaultLocale(Locale.US);
    return sessionLocaleResolver;
  }

//  Moving below to application.properties.
//  @Bean
//  public ResourceBundleMessageSource resourceBundleMessageSource() {
//    ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
//    resourceBundleMessageSource.setBasename("messages");
//    return resourceBundleMessageSource;
//  }
}
