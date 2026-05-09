package com.receipt.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping("/")
    public String home() {
        // This looks into src/main/resources/static/
        return "forward:/index.html"; 
    }
}