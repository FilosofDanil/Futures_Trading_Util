package com.example.binance_api.client;

import com.example.binance_api.models.Message;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram-service", url = "${application.config.telegram-url}")
public interface TelegramClient {
    @PostMapping("")
    void receiveMessage(@RequestBody Message message);
}
