package com.example.binance_api.services.scheduled.impl;

import com.example.binance_api.client.TelegramClient;
import com.example.binance_api.components.PriceComponent;
import com.example.binance_api.models.Alerts;
import com.example.binance_api.models.Message;
import com.example.binance_api.models.Users;
import com.example.binance_api.services.AlertService;
import com.example.binance_api.services.impl.UserServiceBean;
import com.example.binance_api.services.scheduled.IScheduled;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AlertingService implements IScheduled {
    private final UserServiceBean userService;
    private final AlertService alertService;
    private final PriceComponent priceComponent;
    private final TelegramClient telegramClient;

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
                if (alert.getCrossed()) {
                    return;
                }
                if (Objects.equals(currentPrice, alert.getPrice())) {
                    alert.setCrossed(true);
                } else if (alert.getCurrent_price() < alert.getPrice() && currentPrice > alert.getPrice()) {
                    alert.setCrossed(true);
                } else if (alert.getCurrent_price() > alert.getPrice() && currentPrice < alert.getPrice()) {
                    alert.setCrossed(true);
                } else{
                    return;
                }
                alert.setCross_date(new Date());
                alertService.update(user, alert, alert.getId());
                Message message = Message.builder()
                        .message("Alert! Ticker " + alert.getTicker() +" has been crossed price level " + alert.getPrice())
                        .profileName(user.getProfileName())
                        .build();
                telegramClient.receiveMessage(message);
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
                if (keyValue.length == 2 && keyValue[0].equals("\"price\"")) {
                    try {
                        String result = extractDigits(keyValue[1]);
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
