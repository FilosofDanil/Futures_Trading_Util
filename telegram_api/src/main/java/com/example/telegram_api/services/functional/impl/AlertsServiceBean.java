package com.example.telegram_api.services.functional.impl;

import com.example.telegram_api.client.BinanceClient;
import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.entities.Users;
import com.example.telegram_api.services.functional.AlertsService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertsServiceBean implements AlertsService {
    private final BinanceClient client;

    @Override
    public List<Alerts> getAll(Users user) {
        return null;
    }

    @Override
    public Alerts getById(Users user, Long id) {
        return null;
    }

    @Override
    public Alerts create(Alerts alerts) {
        return null;
    }

    @Override
    public void update(Alerts alerts, Long id) {

    }

    @Override
    public void delete(Users user, Long id) {

    }
}
