package com.example.telegram_api.services.functional.impl;

import com.example.telegram_api.client.BinanceClient;
import com.example.telegram_api.services.functional.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceBean implements PriceService {
    private final BinanceClient client;

    @Override
    public String getPrice(String marker) {
        return client.getPrice(marker);
    }
}
