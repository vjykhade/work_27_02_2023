package com.springdemo.web.repository;

import com.springdemo.web.entity.UserH2Entity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserH2Repository extends JpaRepository<UserH2Entity, Integer> {

}
