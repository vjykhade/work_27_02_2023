package com.springdemo.web.controller;

import com.springdemo.web.versioning.Name;
import com.springdemo.web.versioning.PersonV1;
import com.springdemo.web.versioning.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getPerson()
    {
        return new PersonV1("Vijay Khade");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionPerson()
    {
        return new PersonV2(new Name("Vijay","Khade"));
    }

    @GetMapping(path="/person", params = "version=1")
    public PersonV1 getPersonUsingParams()
    {
        return new PersonV1("Vijay Khade");
    }

    @GetMapping(path="/person", params = "version=2")
    public PersonV2 getSecondPersonUsingParams()
    {
        return new PersonV2(new Name("Vijay","Khade"));
    }
}
