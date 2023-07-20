package com.example.security.controller;

import com.example.security.model.Users;
import com.example.security.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/security")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public void login(@RequestBody Users user){
        authService.login(user);
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody Users user){
        authService.signUp(user);
    }
}
