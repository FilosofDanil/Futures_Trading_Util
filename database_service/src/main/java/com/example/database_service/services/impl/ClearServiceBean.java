package com.example.database_service.services.impl;

import com.example.database_service.entities.Alerts;
import com.example.database_service.repositories.AlertRepo;
import com.example.database_service.services.ClearService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClearServiceBean implements ClearService<Alerts> {
    private final AlertRepo alertRepo;

    @Override
    public void clear(List<Alerts> alerts) {
        alertRepo.deleteAll(alerts);
    }
}
