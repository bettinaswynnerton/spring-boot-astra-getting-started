package com.example.astrademostep2;

import org.springframework.stereotype.Component;

@Component
public class GreetingImplementation implements GreetingService {

    @Override
    public String sayHello() {
        return "Hi from the greeting service";
    }
}