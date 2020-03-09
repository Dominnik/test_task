package com.haulmont.testtask.controller;

import org.hibernate.exception.ConstraintViolationException;

public interface Controller<T> {
    T add(T entity);
    void update(T entity);
    void delete(long id) throws ConstraintViolationException;
}
