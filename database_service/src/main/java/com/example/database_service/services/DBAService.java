package com.example.database_service.services;

import java.util.List;

public interface DBAService<T> {
    T getById(Long id);

    List<T> getAll();

    T create(T t);

    void update(T t, Long id);

    void delete(Long id);
}
