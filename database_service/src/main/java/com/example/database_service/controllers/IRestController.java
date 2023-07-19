package com.example.database_service.controllers;

import java.util.List;

public interface IRestController<T> {
    List<T> getAll();

    T getById(Long id);

    T create(T t);

    void update(Long id, T t);

    void delete(Long id);
}
