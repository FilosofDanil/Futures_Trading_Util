package com.example.telegram_api.services.functional.impl;

import com.example.telegram_api.client.BinanceClient;
import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.entities.Users;
import com.example.telegram_api.services.functional.AlertsService;
import com.example.telegram_api.services.functional.ClearService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertsServiceBean implements AlertsService, ClearService {
    private final BinanceClient client;

    @Override
    public List<Alerts> getAll(String username) {
        return client.getAll(username);
    }

    @Override
    public Alerts getById(Users user, Long id) {
        return client.getById(user, id);
    }

    @Override
    public Alerts create(Alerts alerts) {
        return client.create(alerts);
    }

    @Override
    public void update(Alerts alerts, Long id) {
        client.update(id, alerts);
    }

    @Override
    public void delete(Long id) {
        client.delete(id);
    }

    @Override
    public void clear(String username) {
        client.clear(username);
    }
}
