package com.example.demo.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/greet")
public class Greet {

    @GetMapping("/login")
    public String login() {
        return "Login success!";
    }

    @GetMapping("/hi")
    public String sayHello() {
        return "Hello!";
    }

    @PostMapping("/protected/hi")
    public String hello(@RequestBody String name) {
        return String.format("Hello! %s", name);
    }
}
