package com.example.telegram_api.services.functional;

import com.example.telegram_api.models.telegram_entities.LoginRequest;
import com.example.telegram_api.models.telegram_entities.UsernameModel;
import com.example.telegram_api.models.entities.Users;

public interface RegistryService {
    void signUp(Users users);

    void activate(String code, UsernameModel usernameModel);

    Boolean login(LoginRequest request);
}
