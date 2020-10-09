package com.example.astrademostep2;

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

    @GetMapping("/greeting")
    public String  hello(){
        return "hello world!!!";
    }

}