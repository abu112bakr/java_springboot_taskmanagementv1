package com.example.taskmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "Please enter your credentials to log in.";
    }
    
}
