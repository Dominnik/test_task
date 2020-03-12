package com.haulmont.testtask.model.dao;

import com.haulmont.testtask.model.entity.Dto;
import org.hibernate.exception.ConstraintViolationException;

import java.util.List;

public interface Dao<T extends Dto> {
    List<T> getAll();

    T get(long id);

    void add(T entity);

    void update(T entity);

    void delete(long id) throws ConstraintViolationException;
}
