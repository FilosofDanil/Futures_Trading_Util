package com.example.telegram_api.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "binance-service", url = "${application.config.binance-url}")
public interface BinanceClient {
    @GetMapping("/price/{marker}")
    String getPrice(@PathVariable("marker") String marker);
}
