package com.example.database_service.controllers.impl;

import com.example.database_service.controllers.IRestController;
import com.example.database_service.entities.Alerts;
import com.example.database_service.services.DBAService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("api/v1/data/alerts")
@RequiredArgsConstructor
public class AlertController implements IRestController<Alerts> {
    private final DBAService<Alerts> alertsDBAService;

    @Override
    @GetMapping("/")
    public List<Alerts> getAll() {
        return alertsDBAService.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Alerts getById(@PathVariable("id") Long id) {
        return alertsDBAService.getById(id);
    }

    @Override
    @PostMapping("/")
    public Alerts create(@RequestBody Alerts alerts) {
        return alertsDBAService.create(alerts);
    }

    @Override
    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody Alerts alerts) {
        alertsDBAService.update(alerts, id);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        alertsDBAService.delete(id);
    }
}
