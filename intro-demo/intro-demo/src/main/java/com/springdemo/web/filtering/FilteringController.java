package com.springdemo.web.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean filtering()
    {
        return new SomeBean("value1","value2","value3");
    }
    @GetMapping("/filtering-1")
    public MappingJacksonValue filteringOnDynamic()
    {
        SomeBean someBean = new SomeBean("value1","value2","value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("SomeBeanFilter",simpleBeanPropertyFilter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
  }
}
