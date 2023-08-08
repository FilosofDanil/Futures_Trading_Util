package com.example.binance_api.services.impl;

import com.example.binance_api.client.DataClient;
import com.example.binance_api.models.Alerts;
import com.example.binance_api.models.Users;
import com.example.binance_api.services.AlertService;
import com.example.binance_api.services.ClearAlertsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertServiceBean implements AlertService, ClearAlertsService {
    private final DataClient client;

    @Override
    public List<Alerts> getAll(String username) {
        return getAllAlerts(username);
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
        alert.setUser(client.getAllUsers().stream().filter(users -> users.getProfileName().equals(user.getProfileName())).findFirst().get());
        client.createAlert(alert);
        return alert;
    }

    @Override
    public Alerts update(Users user, Alerts alert, Long id) {
        client.updateAlert(id, alert);
        return alert;
    }

    @Override
    public void clear(String username) {
        List<Alerts> list = getAllAlerts(username).stream().filter(Alerts::getCrossed).toList();
        client.clear(list);
    }

    private List<Alerts> getAllAlerts(String username) {
        return client.getAllAlerts().stream().filter(alerts -> alerts.getUser().getProfileName().equals(username)).collect(Collectors.toList());
    }
}
