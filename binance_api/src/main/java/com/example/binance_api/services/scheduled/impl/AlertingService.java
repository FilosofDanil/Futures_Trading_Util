package com.example.binance_api.services.scheduled.impl;

import com.example.binance_api.components.PriceComponent;
import com.example.binance_api.models.Alerts;
import com.example.binance_api.models.Users;
import com.example.binance_api.services.AlertService;
import com.example.binance_api.services.impl.UserServiceBean;
import com.example.binance_api.services.scheduled.IScheduled;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AlertingService implements IScheduled {
    private final UserServiceBean userService;
    private final AlertService alertService;
    private final PriceComponent priceComponent;


    @Override
    @Scheduled(cron = "*/1 * * * * *")
    public void scheduledTask() {
        List<Users> users = userService.getAll();
        users.forEach(user -> {
            List<Alerts> alerts = alertService.getAll(user);
            alerts.forEach(alert -> {
                Double currentPrice = 0.0;
                if (Objects.equals(currentPrice, alert.getPrice())) {
                    alert.setCrossed(true);
                    alertService.update(user, alert, alert.getId());
                }
            });
        });

    }

    private static class DoubleParser {
        private static Double parse(String toParse) {
            return null;
        }
    }
}
