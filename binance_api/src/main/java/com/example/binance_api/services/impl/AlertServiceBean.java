package com.example.binance_api.services.impl;

import com.example.binance_api.client.DataClient;
import com.example.binance_api.models.Alerts;
import com.example.binance_api.models.Users;
import com.example.binance_api.services.AlertService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertServiceBean implements AlertService {
    private final DataClient client;

    @Override
    public List<Alerts> getAll(Users user) {
        return client.getAllAlerts().stream().filter(alerts -> alerts.getUser().equals(user)).collect(Collectors.toList());
    }

    @Override
    public Alerts getById(Users user, Long id) {
        return client.getAlertById(id);
    }

    @Override
    public void delete(Users user, Long id) {
        client.deleteAlert(id);
    }

    @Override
    public Alerts create(Users user, Alerts alert) {
        client.createAlert(alert);
        return alert;
    }

    @Override
    public Alerts update(Users user, Alerts alert, Long id) {
        client.updateAlert(id, alert);
        return alert;
    }
}
