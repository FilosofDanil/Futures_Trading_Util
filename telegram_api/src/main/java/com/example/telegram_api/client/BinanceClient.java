package com.example.telegram_api.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "database-service", url = "${application.config.binance-url}")
public interface BinanceClient {
}
