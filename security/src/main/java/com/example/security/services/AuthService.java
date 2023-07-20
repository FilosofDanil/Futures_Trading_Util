package com.example.security.services;

import com.example.security.model.Users;

public interface AuthService {
    Boolean login(String username);

    void signUp(Users user);

    void activate(String code, String username);
}
