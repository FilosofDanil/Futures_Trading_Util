package com.example.binance_api.services.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.connector.futures.client.impl.UMFuturesClientImpl;
import com.binance.connector.futures.client.impl.futures.Market;
import com.example.binance_api.components.PriceComponent;
import com.example.binance_api.services.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class PriceServiceBean implements PriceService {
    private final PriceComponent priceComponent;
    @Override
    public String getPrice(String marker) {
      return priceComponent.getPrice(marker);
    }
}
