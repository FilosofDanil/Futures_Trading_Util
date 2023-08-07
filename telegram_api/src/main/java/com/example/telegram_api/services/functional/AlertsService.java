package com.example.telegram_api.services.functional;

import com.example.telegram_api.models.entities.Alerts;
import com.example.telegram_api.models.entities.Users;

import java.util.List;

public interface AlertsService {
    List<Alerts> getAll(String username);

    Alerts getById(Users user, Long id);

    Alerts create (Alerts alerts);

    void update (Alerts alerts, Long id);

    void delete (Users user, Long id);
}
