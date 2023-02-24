package com.springdemo.web.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.springdemo.web.doa.UserDoaService;
import com.springdemo.web.entity.User;
import com.springdemo.web.exceptionhandling.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.ServiceConfigurationError;

@RestController
public class UserController {

    private UserDoaService service;

    public UserController(UserDoaService service)
    {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retriveAllUsers()
    {
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retriveOneUser(@PathVariable int id)
    {
        User user = service.findOne(id);
        if (user == null)
            throw new UserNotFoundException("id: "+id);
        EntityModel<User> entityModel = EntityModel.of(user);
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retriveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@Valid @RequestBody User user)
    {
        User savedUser = service.saveUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id)
    {
        service.deleteById(id);
    }
}
