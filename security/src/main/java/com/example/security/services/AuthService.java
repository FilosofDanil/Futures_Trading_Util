package com.example.security.services;

import com.example.security.model.UsernameModel;
import com.example.security.model.*;

public interface AuthService {
    Boolean login(LoginRequest loginRequest);

    void signUp(Users user);

    void activate(String code, UsernameModel username);
}
