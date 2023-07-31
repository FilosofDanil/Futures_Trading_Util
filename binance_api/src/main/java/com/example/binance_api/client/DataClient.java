package com.example.binance_api.client;

import com.example.binance_api.models.Alerts;
import com.example.binance_api.models.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "database-service", url = "${application.config.database-url}")
public interface DataClient {
    @GetMapping("/alerts/")
    List<Alerts> getAllAlerts();
    @GetMapping("/alerts/{id}")
    Alerts getAlertById(@PathVariable("id") Long id);
    @DeleteMapping("/alerts/{id}")
    Alerts deleteAlert(@PathVariable("id") Long id);
    @PutMapping("/alerts/{id}")
    void updateAlert(@PathVariable("id") Long id, @RequestBody Alerts alert);
    @PostMapping("/alerts/")
    void createAlert(@RequestBody Alerts alert);
    @GetMapping("/users/")
    List<Users> getAllUsers();
}
