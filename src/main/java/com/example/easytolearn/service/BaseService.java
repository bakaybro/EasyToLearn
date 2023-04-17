package com.example.easytolearn.service;

import java.util.List;

public interface BaseService<T> {
    T save(T t);

    T getById(Long id);

    List<T> getAll();
}