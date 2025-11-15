package com.cakemaker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CakeController {

    @GetMapping("/")
    public String welcomeMessage() {
        return "Hello cake maker, your cake is being cooked!";
    }

    @GetMapping("/status")
    public String cakeStatus() {
        return "Your cake is being cooked";
    }
}