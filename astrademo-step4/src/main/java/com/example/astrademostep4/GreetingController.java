package com.example.astrademostep4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @Autowired
    private GreetingService greetingservice;


    @GetMapping("/greeting")
    public String  hello(){
        return greetingservice.sayHello();
    }

    @GetMapping("/language")
    public String hellolanguage(@RequestParam(value="language") String language){
        return greetingservice.sayHelloLanguage(language);
    }

    @GetMapping("/insert")
    public  String insertGreeting(@RequestParam(value="language") String language,
                                  @RequestParam(value="greeting") String greeting){
        return greetingservice.insertGreeting(language, greeting);
    }

}