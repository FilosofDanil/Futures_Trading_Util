package com.example.database_service.services.impl;

import com.example.database_service.entities.Users;
import com.example.database_service.repositories.UserRepo;
import com.example.database_service.services.UserAdviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAdviceServiceBean implements UserAdviceService {
    private final UserRepo userRepo;


    @Override
    public Users findByName(String profileName) {
        return userRepo.findUsersByProfileName(profileName);
    }
}
