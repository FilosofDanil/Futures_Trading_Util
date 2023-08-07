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
    public List<Alerts> getAll(String username) {
        return client.getAll(username);
    }

    @Override
    public Alerts getById(Users user, Long id) {
        return client.getById(user, id);
    }

    @Override
    public Alerts create(Alerts alerts) {
//        return client.create(alerts);
        return null;
    }

    @Override
    public void update(Alerts alerts, Long id) {
        client.update(id, alerts);
    }

    @Override
    public void delete(Users user, Long id) {
        client.delete(user, id);
    }
}
