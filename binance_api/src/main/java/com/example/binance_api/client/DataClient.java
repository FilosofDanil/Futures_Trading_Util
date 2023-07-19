package com.example.binance_api.client;

import com.example.binance_api.components.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "database-service", url = "${application.config.database-url}")
public interface DataClient {
    @GetMapping("/users/")
    public List<Users> getAll();
}
