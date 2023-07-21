package com.example.database_service.services;

import com.example.database_service.entities.Users;

public interface UserAdviceService {
    Users findByName(String profileName);
}
