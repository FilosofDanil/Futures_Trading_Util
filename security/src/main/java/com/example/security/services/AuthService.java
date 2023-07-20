package com.example.security.services;

import com.example.security.model.UsernameModel;
import com.example.security.model.Users;

public interface AuthService {
    Boolean login(UsernameModel username);

    void signUp(Users user);

    void activate(String code, UsernameModel username);
}
