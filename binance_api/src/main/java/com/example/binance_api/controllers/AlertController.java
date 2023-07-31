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

    @GetMapping("")
    public ResponseEntity<List<Alerts>> getAll(@RequestBody Users user) {
        return new ResponseEntity<>(alertService.getAll(user), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alerts> getById(@RequestBody Users user, @PathVariable("id") Long id) {
        return new ResponseEntity<>(alertService.getById(user, id), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Alerts> create(@RequestBody Users user, @RequestBody Alerts alerts) {
        return new ResponseEntity<>(alertService.create(user, alerts), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Alerts> update(@RequestBody Users user, @PathVariable("id") Long id, @RequestBody Alerts alerts) {
        return new ResponseEntity<>(alertService.update(user, alerts, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@RequestBody Users user, @PathVariable("id") Long id) {
        alertService.delete(user, id);
        return HttpStatus.OK;
    }
}
