package com.example.binance_api.services.impl;

import com.example.binance_api.components.PriceComponent;
import com.example.binance_api.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceServiceBean implements PriceService {
    private final PriceComponent priceComponent;
    @Override
    public String getPrice(String marker) {
      return priceComponent.getPrice(marker);
    }
}
