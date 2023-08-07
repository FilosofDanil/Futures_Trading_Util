package com.example.binance_api.controllers;

import com.example.binance_api.models.Alerts;
import com.example.binance_api.models.Users;
import com.example.binance_api.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/binance/alerts")
public class AlertController {
    private final AlertService alertService;

    @GetMapping("/all/{username}")
    public ResponseEntity<List<Alerts>> getAll(@PathVariable String username) {
        System.out.println("get");
        return new ResponseEntity<>(alertService.getAll(username), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alerts> getById(@RequestBody Users user, @PathVariable("id") Long id) {
        return new ResponseEntity<>(alertService.getById(user, id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Alerts> create(@RequestBody Alerts alerts) {
        System.out.println("post");
        return new ResponseEntity<>(alertService.create(alerts.getUser(), alerts), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alerts> update(@PathVariable("id") Long id, @RequestBody Alerts alerts) {
        return new ResponseEntity<>(alertService.update(alerts.getUser(), alerts, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@RequestBody Users user, @PathVariable("id") Long id) {
        alertService.delete(user, id);
        return HttpStatus.OK;
    }
}
