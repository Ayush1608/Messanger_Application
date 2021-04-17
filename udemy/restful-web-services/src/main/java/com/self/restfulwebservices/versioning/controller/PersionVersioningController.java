package com.self.restfulwebservices.versioning.controller;


import com.self.restfulwebservices.versioning.model.Name;
import com.self.restfulwebservices.versioning.model.PersonV1;
import com.self.restfulwebservices.versioning.model.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PersionVersioningController {

  /**
   * The most basic versioning
   */

  @GetMapping("v1/person")
  public PersonV1 getPersonV1() {
    return new PersonV1("name");
  }

  @GetMapping("v2/person")
  public PersonV2 getPersonV2() {
    return new PersonV2(new Name("name", "surname"));
  }

  /**
   * A few more ways of versioning.
   */

  @GetMapping(value = "person/param", params = "version=1")
  public PersonV1 getPersonV1UsingParam() {
    return new PersonV1("name");
  }

  @GetMapping(value = "person/param", params = "version=2")
  public PersonV2 getPersonV2UsingParam() {
    return new PersonV2(new Name("name", "surname"));
  }

  @GetMapping(value = "person/header", headers = "X-API_VERSION=1")
  public PersonV1 getPersonV1UsingHeader() {
    return new PersonV1("name");
  }

  @GetMapping(value = "person/header", headers = "X-API_VERSION=2")
  public PersonV2 getPersonV2UsingHeader() {
    return new PersonV2(new Name("name", "surname"));
  }

  @GetMapping(value = "person/produces", produces = "application/app-v1+json")
  public PersonV1 getPersonV1UsingAcceptHeader() {
    return new PersonV1("name");
  }

  @GetMapping(value = "person/produces", produces = "application/app-v2+json")
  public PersonV2 getPersonV2UsingAcceptHeader() {
    return new PersonV2(new Name("name", "surname"));
  }

}
