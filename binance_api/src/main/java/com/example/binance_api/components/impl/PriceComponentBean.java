package com.example.binance_api.components.impl;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerStatistics;
import com.binance.connector.futures.client.impl.futures.Market;
import com.example.binance_api.components.PriceComponent;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class PriceComponentBean implements PriceComponent {
    @Override
    public String getPrice(String marker) {
        Market market = new Market("https://fapi.binance.com/fapi", "API-KEY", "SECRET", false, null) {
        };
        LinkedHashMap<String,Object> map= new LinkedHashMap<>();
        map.put("symbol", marker);
        return market.tickerSymbol(map);
    }
}
