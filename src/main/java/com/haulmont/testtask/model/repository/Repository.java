package com.haulmont.testtask.model.repository;

import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface Repository<T> {
    void addUpdateListener(UpdateEntityListener listener);
    List<T> getAll();
    T get(long id);
    T add(T entity);
    void update(T entity);
    void delete(long id) throws ConstraintViolationException;

    interface UpdateEntityListener {
        void onUpdated();
    }
}
