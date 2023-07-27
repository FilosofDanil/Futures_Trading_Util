package com.example.database_service.services.impl;

import com.example.database_service.entities.Alerts;
import com.example.database_service.entities.Users;
import com.example.database_service.repositories.UserRepo;
import com.example.database_service.services.DBAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDBAService implements DBAService<Users> {
    private final UserRepo userRepo;

    @Override
    public Users getById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public List<Users> getAll() {
        List<Users> returnList = new ArrayList<>();
        userRepo.findAll().forEach(returnList::add);
        return returnList;
    }

    @Override
    public Users create(Users user) {
        if (userRepo.findUsersByProfileName(user.getProfileName()) != null || userRepo.findUsersByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Invalid data, or these resource is already exist!");
        }
        userRepo.save(user);
        return user;
    }

    @Override
    public void update(Users user, Long id) {
        userRepo.findById(id).map(users -> {
            users.setVerified(user.getVerified());
            users.setActivationCode(user.getActivationCode());
            users.setProfileName(user.getProfileName());
            users.setEmail(user.getEmail());
            users.setPassword(user.getPassword());
            return userRepo.save(users);
        });
    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
