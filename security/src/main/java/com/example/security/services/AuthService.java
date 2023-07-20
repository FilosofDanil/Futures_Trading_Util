package com.example.security.services;

import com.example.security.model.Users;

public interface AuthService {
    void login(Users user);

    void signUp(Users user);
}
