package com.example.binance_api.services.scheduled;

import org.springframework.scheduling.annotation.Scheduled;

public interface IScheduled {
    @Scheduled(fixedDelay = 1000, fixedRate = 1000)
    void scheduledTask();
}
