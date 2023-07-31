package com.example.binance_api.services.impl;

import com.example.binance_api.client.DataClient;
import com.example.binance_api.models.Users;
import com.example.binance_api.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceBean implements UserService {
    private final DataClient client;

    @Override
    public List<Users> getAll() {
        return client.getAllUsers();
    }
}
