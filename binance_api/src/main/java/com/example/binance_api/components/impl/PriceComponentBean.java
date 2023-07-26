package com.example.binance_api.components.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import com.example.binance_api.components.PriceComponent;

import java.util.LinkedHashMap;

public class PriceComponentBean implements PriceComponent {
    @Override
    public String getPrice(String marker) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
        BinanceApiRestClient client = factory.newRestClient();
        TickerStatistics tickerStatistics = client.get24HrPriceStatistics(marker);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("symbol", "OCEANUSDT");
        return tickerStatistics.getLastPrice();
    }
}
