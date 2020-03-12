package com.haulmont.testtask.controller;

import com.haulmont.testtask.model.entity.Dto;
import org.hibernate.exception.ConstraintViolationException;

public interface Controller<T extends Dto> {
    void add(T entity);

    void update(T entity);

    void delete(long id) throws ConstraintViolationException;
}
