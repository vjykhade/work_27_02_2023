package com.springdemo.web.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity
@Table(name="user_details")
public class UserH2Entity {
    @Id
    @GeneratedValue
    private int id;
    @Size(min=2, message = "Name should be at least 2 characters")
    private String name;
    @Past(message = "Birth Date should in be past date only")
    private LocalDate birthDate;

    protected UserH2Entity() {

    }

    public UserH2Entity(int id, String name, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "UserH2Entity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
