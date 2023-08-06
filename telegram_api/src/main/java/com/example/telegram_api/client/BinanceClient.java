package com.example.telegram_api.client;

import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.entities.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "binance-service", url = "${application.config.binance-url}")
public interface BinanceClient {
    @GetMapping("/price/{marker}")
    String getPrice(@PathVariable("marker") String marker);
    @GetMapping("/alerts")
    List<Alerts> getAll(@RequestBody Users user);
    @GetMapping("/{id}")
    Alerts getById(@RequestBody Users user, @PathVariable("id") Long id);
    @PostMapping("/alerts")
    Alerts create(@RequestBody Users user, @RequestBody Alerts alerts);
    @PutMapping("/{id}")
    Alerts update(@RequestBody Users user, @PathVariable("id") Long id, @RequestBody Alerts alerts);
    @DeleteMapping("/{id}")
    void delete(@RequestBody Users user, @PathVariable("id") Long id);
}
