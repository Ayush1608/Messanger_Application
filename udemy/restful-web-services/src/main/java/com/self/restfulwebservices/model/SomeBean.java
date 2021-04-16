package com.self.restfulwebservices.model;


import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
//@JsonIgnoreProperties(value = {"field1", "field2"})  One more way of static filtering
@JsonFilter("SomeBeanFilter")
public class SomeBean {

  private String field1;

  // This is static filtering. This field will not be present in API response.
  @JsonIgnore
  private String field2;
  private String field3;
  private String field4;
}
