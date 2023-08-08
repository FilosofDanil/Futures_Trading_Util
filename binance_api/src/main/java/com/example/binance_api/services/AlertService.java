package com.example.binance_api.services;

import com.example.binance_api.models.Alerts;
import com.example.binance_api.models.Users;

import java.util.List;

public interface AlertService {
    List<Alerts> getAll(String username);

    Alerts getById(Users user, Long id);

    void delete(Users user, Long id);

    Alerts create(Users user, Alerts alert);

    Alerts update(Users user, Alerts alert, Long id);
}
