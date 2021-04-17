package com.self.restfulwebservices.filtering.controller;


import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.self.restfulwebservices.filtering.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FilteringController {

  @GetMapping("/static-filter") // To run this api comment out @JsonFilter("SomeBeanFilter") in SomeBean.java
  public SomeBean staticFilter() {
    return SomeBean.builder()
      .field1("value1")
      .field2("value2")
      .field3("value3")
      .field4("va;ue4")
      .build();
  }

  @GetMapping("/dynamic-filter")
  public MappingJacksonValue dynamicFilter() {
    SomeBean someBean = SomeBean.builder()
                                .field1("value1")
                                .field2("value2")
                                .field3("value3")
                                .field4("value4")
                                .build();

    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

    // SomeBeanFilter provided below should also be added to SomeBean.
    FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter", simpleBeanPropertyFilter);
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
    mappingJacksonValue.setFilters(filterProvider);
    return mappingJacksonValue;
  }
}
