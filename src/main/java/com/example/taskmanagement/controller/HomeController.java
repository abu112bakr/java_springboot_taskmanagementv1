package com.example.taskmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String greet(){
        return "Welcome to the Task Management System!";
    }
    @RequestMapping("/about")
    public String about(){
        return "This application helps you manage your tasks efficiently.";
    }
}
