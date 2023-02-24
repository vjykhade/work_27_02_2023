package com.springdemo.web.controller;

import com.springdemo.web.entity.UserH2Entity;
import com.springdemo.web.repository.UserH2Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserH2Controller {

    UserH2Repository userH2Repository ;

    public UserH2Controller(UserH2Repository userH2Repository) {
        this.userH2Repository = userH2Repository;
    }

    @GetMapping("/jpa/users")
    public List<UserH2Entity> getUsersFromH2()
    {
        return userH2Repository.findAll();
    }

    @PostMapping("/jpa/users")
    public UserH2Entity saveUserFromH2(@RequestBody UserH2Entity userH2Entity)
    {
        return userH2Repository.save(userH2Entity);
    }
}
