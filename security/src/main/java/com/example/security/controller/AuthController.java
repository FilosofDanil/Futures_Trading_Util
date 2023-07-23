package com.example.security.controller;

import com.example.security.model.UsernameModel;
import com.example.security.model.Users;
import com.example.security.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> login(@RequestBody UsernameModel username) {
        return new ResponseEntity<>(authService.login(username), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<Users> signUp(@RequestBody Users user) {
        authService.signUp(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/activate/{code}")
    public ResponseEntity<String> signUp(@PathVariable String code, @RequestBody UsernameModel username) {
        authService.activate(code, username);
        return new ResponseEntity<>(code, HttpStatus.OK);
    }
}
