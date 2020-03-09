package com.haulmont.testtask.model.dao;

import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();
    T get(long id);
    T add(T entity);
    void update(T entity);
    void delete(long id) throws ConstraintViolationException;
}
