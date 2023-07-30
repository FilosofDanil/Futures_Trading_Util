package com.example.binance_api.services.scheduled.impl;

import com.example.binance_api.client.DataClient;
import com.example.binance_api.services.scheduled.IScheduled;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlertingService implements IScheduled {
    private final DataClient client;


    @Override
    public void scheduledTask() {

    }
}
