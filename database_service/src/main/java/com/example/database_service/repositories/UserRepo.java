package com.example.database_service.repositories;

import com.example.database_service.entities.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<Users, Long> {
    Users findUsersByProfileName(String profileName);

    Users findUsersByEmail(String email);
}
