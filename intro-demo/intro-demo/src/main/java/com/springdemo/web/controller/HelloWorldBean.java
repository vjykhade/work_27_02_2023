package com.springdemo.web.controller;

public class HelloWorldBean {

    private final String message;

    public HelloWorldBean(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public String setMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                '}';
    }

}
