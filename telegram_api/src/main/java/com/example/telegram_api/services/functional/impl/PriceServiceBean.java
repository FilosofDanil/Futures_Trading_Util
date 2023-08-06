package com.example.telegram_api.services.functional.impl;

import com.example.telegram_api.client.BinanceClient;
import com.example.telegram_api.services.functional.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PriceServiceBean implements PriceService {
    private final BinanceClient client;

    @Override
    public String getPrice(String marker) {
        return Objects.requireNonNull(DoubleParser.extractPrice(client.getPrice(marker))).toString();
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
