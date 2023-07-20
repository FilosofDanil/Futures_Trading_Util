package com.example.security.client;

import com.example.security.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "database-service", url = "${application.config.database-url}")
public interface DataClient {
    @GetMapping("/user/{name}")
    Users getUserByName(@PathVariable("name") String name);

    @PostMapping("/")
    Users create(@RequestBody Users users);

    @PutMapping("/{id}")
    void update(@PathVariable("id") Long id, @RequestBody Users users);

    @GetMapping("/{id}")
    Users getById(@PathVariable("id") Long id);
}