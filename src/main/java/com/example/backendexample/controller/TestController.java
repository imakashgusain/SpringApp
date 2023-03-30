package com.example.backendexample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/")
    public String all(){
        return "Welcome all";
    }

    @GetMapping("/user")
    public String user(){
        return "Welcome user";
    }
    @GetMapping("/admin")
    public String admin(){
        return "Welcome admin";
    }
}
