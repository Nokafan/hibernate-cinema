package com.dev.cinema.dao;

public interface GenericDao<T> {
    T add(T entity);

    T get(Long id);
}
