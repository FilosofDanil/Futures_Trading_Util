package com.example.database_service.controllers;

import com.example.database_service.entities.Alerts;

import java.util.List;

public interface ClearController<T> {
    void clear(List<T> T);
}
