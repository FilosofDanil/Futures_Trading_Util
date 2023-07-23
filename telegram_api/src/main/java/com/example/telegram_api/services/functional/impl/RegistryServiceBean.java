package com.example.telegram_api.services.functional.impl;

import com.example.telegram_api.client.DataClient;
import com.example.telegram_api.models.Users;
import com.example.telegram_api.services.functional.RegistryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistryServiceBean implements RegistryService {
    private final DataClient client;

    @Override
    public void signUp(Users users) {
        client.signUp(users);
    }
}
