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
                Double currentPrice = DoubleParser.extractPrice(priceComponent.getPrice(alert.getTicker()));
                if (currentPrice == null) {
                    throw new NullPointerException("Something wrong with ticker price. Or this ticker is undefined");
                }
                if(alert.getCrossed()){
                    return;
                }
                if (Objects.equals(currentPrice, alert.getPrice())) {
                    alert.setCrossed(true);
                    alertService.update(user, alert, alert.getId());
                } else if (alert.getCurrent_price() < alert.getPrice() && currentPrice > alert.getPrice()) {
                    alert.setCrossed(true);
                    alertService.update(user, alert, alert.getId());
                } else if (alert.getCurrent_price() > alert.getPrice() && currentPrice < alert.getPrice()) {
                    alert.setCrossed(true);
                    alertService.update(user, alert, alert.getId());
                }
                System.out.println(alert);
            });
        });

    }

    private static class DoubleParser {
        private static String extractDigits(String input) {
            return input.replaceAll("\"", "");
        }

        private static Double extractPrice(String jsonString) {
            // Remove curly braces and whitespace characters
            jsonString = jsonString.replaceAll("\\{|\\}|\\s", "");

            // Split the string into key-value pairs
            String[] keyValuePairs = jsonString.split(",");
            // Search for the "price" parameter and extract its value
            for (String pair : keyValuePairs) {
                String[] keyValue = pair.split(":");
                System.out.println(keyValue[0] + keyValue[1]);
                if (keyValue.length == 2 && keyValue[0].equals("\"price\"")) {
                    try {
                        String result = extractDigits(keyValue[1]);
                        System.out.println("Extracted digits: " + result);
                        return Double.parseDouble(result);
                    } catch (NumberFormatException e) {
                        // If there's an error parsing the double value, return null
                        return null;
                    }
                }
            }

            // If the "price" parameter is not found, return null
            return null;
        }
    }
}
