package com.work22022023.microservices.currencyexchangeservice.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    @Retry(name="sample-api", fallbackMethod="hardCodedResponse")
    //@CircuitBreaker(name="default",fallbackMethod="hardCodedResponse")
    //@RateLimiter(name="default")
    @Bulkhead(name="sample-api")
    public String sampleApi()
    {
        logger.info("Sample API call received");
        //ResponseEntity<String> forEntity=  new RestTemplate().getForEntity("http://localhost:8080/some-dummey-url",String.class);
        return "sample-api";
    }
    public String hardCodedResponse(Exception exception)
    {
        return "fallback-response";
    }
}
