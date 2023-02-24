package com.springdemo.web.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class welcomeController {

    private MessageSource messageSource;

    public welcomeController(MessageSource messageSource)
    {
        this.messageSource = messageSource;
    }
    @GetMapping("/hello")
    public String helloWorld()
    {
        return "Hello World";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean()
    {
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("/hello/{name}")
    public HelloWorldBean helloPathVariable(@PathVariable String name)
    {
        return new HelloWorldBean("Hello, "+name);
    }

    @GetMapping("/i18n")
    public String introInternationalization()
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default Message",locale);

    }
}
