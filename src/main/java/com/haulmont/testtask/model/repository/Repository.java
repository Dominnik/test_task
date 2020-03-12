package com.haulmont.testtask.model.repository;

import com.haulmont.testtask.model.entity.Dto;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface Repository<T extends Dto> {
    void addUpdateListener(UpdateEntityListener listener);

    List<T> getAll();

    T get(long id);

    void add(T entity);

    void update(T entity);

    void delete(long id) throws ConstraintViolationException;

    interface UpdateEntityListener {
        void onUpdated();
    }
}
