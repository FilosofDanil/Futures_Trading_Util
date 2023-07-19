package com.example.database_service.services.impl;

import com.example.database_service.entities.Alerts;
import com.example.database_service.repositories.AlertRepo;
import com.example.database_service.services.DBAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlertDBAService implements DBAService<Alerts> {
    private final AlertRepo alertRepo;

    @Override
    public Alerts getById(Long id) {
        return alertRepo.findById(id).get();
    }

    @Override
    public List<Alerts> getAll() {
        List<Alerts> returnList = new ArrayList<>();
        alertRepo.findAll().forEach(returnList::add);
        return returnList;
    }

    @Override
    public Alerts create(Alerts alerts) {
        alertRepo.save(alerts);
        return alerts;
    }

    @Override
    public void update(Alerts alerts, Long id) {
        Alerts alert = alertRepo.findById(id).get();
        alert.setDate(alert.getDate());
        alert.setPrice(alert.getPrice());
        alert.setTicker(alert.getTicker());
        alert.setUser(alert.getUser());
        alert.setCross_date(alert.getCross_date());
        alertRepo.save(alert);
    }

    @Override
    public void delete(Long id) {
        alertRepo.deleteById(id);
    }
}
