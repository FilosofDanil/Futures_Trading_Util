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
        System.out.println(alert);
        System.out.println(alerts);
        alert.setDate(alerts.getDate());
        alert.setPrice(alerts.getPrice());
        alert.setTicker(alerts.getTicker());
        alert.setUser(alerts.getUser());
        alert.setCrossed(alerts.getCrossed());
        alert.setCross_date(alerts.getCross_date());
        alert.setCurrent_price(alerts.getCurrent_price());
        alertRepo.save(alert);
    }

    @Override
    public void delete(Long id) {
        alertRepo.deleteById(id);
    }
}
