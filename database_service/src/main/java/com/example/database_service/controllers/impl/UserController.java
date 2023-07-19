package com.example.database_service.controllers.impl;

import com.example.database_service.controllers.IRestController;
import com.example.database_service.entities.Users;
import com.example.database_service.services.DBAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/data/users")
@RequiredArgsConstructor
public class UserController implements IRestController<Users> {
    private final DBAService<Users> usersDBAService;

    @Override
    @GetMapping("/")
    public List<Users> getAll() {
        return usersDBAService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Users getById(@PathVariable("id") Long id) {
        return usersDBAService.getById(id);
    }

    @Override
    @PostMapping("/")
    public Users create(@RequestBody Users users) {
        return usersDBAService.create(users);
    }

    @Override
    @PutMapping("/{id}")
    public void update(Long id, Users users) {
        usersDBAService.update(users, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        usersDBAService.delete(id);
    }
}
