package com.example.database_service.repositories;

import com.example.database_service.entities.Alerts;
import org.springframework.data.repository.CrudRepository;

public interface AlertRepo extends CrudRepository<Alerts, Long> {
}
