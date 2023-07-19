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
        userRepo.save(user);
        return user;
    }

    @Override
    public void update(Users user, Long id) {

    }

    @Override
    public void delete(Long id) {
        userRepo.deleteById(id);
    }
}
