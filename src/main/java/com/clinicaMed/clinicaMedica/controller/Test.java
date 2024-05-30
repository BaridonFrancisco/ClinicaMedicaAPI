package com.clinicaMed.clinicaMedica.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class Test {
    @GetMapping
    public String greetings(){
        return "Hello world";
    }
}
