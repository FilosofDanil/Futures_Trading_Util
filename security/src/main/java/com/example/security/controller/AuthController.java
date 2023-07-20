package com.example.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/security")
public class AuthController {
    @PostMapping
    public void login(){

    }

    @PostMapping
    public void signUp(){

    }
}
