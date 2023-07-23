package com.example.telegram_api.services.functional;

import com.example.telegram_api.models.UsernameModel;
import com.example.telegram_api.models.Users;

public interface RegistryService {
    void signUp(Users users);

    void activate(String code, UsernameModel usernameModel);

    void login(Users users);
}
