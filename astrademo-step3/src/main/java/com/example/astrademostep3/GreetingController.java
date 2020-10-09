package com.example.astrademostep3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingservice;


    @GetMapping("/greeting")
    public String  hello(){
        return greetingservice.sayHello();
    }

}