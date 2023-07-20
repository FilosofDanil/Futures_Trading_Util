package com.example.security.controller;

import com.example.security.model.UsernameModel;
import com.example.security.model.Users;
import com.example.security.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/v1/security")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public Boolean login(@RequestBody String username) {
        return authService.login(username);
    }

    @PostMapping("/signUp")
    public void signUp(@RequestBody Users user) {
        authService.signUp(user);
    }

    @PostMapping("/activate/{code}")
    public void signUp(@PathVariable String code, @RequestBody UsernameModel username) {
        authService.activate(code, username);
    }
}
