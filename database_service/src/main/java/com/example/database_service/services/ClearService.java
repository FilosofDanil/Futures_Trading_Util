package com.example.database_service.services;

import com.example.database_service.entities.Alerts;

import java.util.List;

public interface ClearService <T> {
    void clear(List<T> T);
}
