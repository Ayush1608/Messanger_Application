package com.self.restfulwebservices.config;


import java.util.ArrayList;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

  public static final Contact CONTACT = new Contact("Ayush Jain","https://www.linkedin.com/in/ayush-1608/","jain.ayush160895@gmail.com");
  public static final ApiInfo DEFAULT_API_INFO = new ApiInfo("Api Documentation",
                                                             "Api Documentation for Restful Web Services",
                                                             "1.0",
                                                             "urn:tos",
                                                             CONTACT,
                                                             "Apache 2.0",
                                                             "http://www.apache.org/licenses/LICENSE-2.0",
                                                             Collections.emptyList());

  @Bean
  public Docket apiDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
             .apiInfo(DEFAULT_API_INFO)
      .produces(Collections.singleton("application/json"));
  }

}
