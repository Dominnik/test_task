package com.haulmont.testtask.controller;

import com.google.inject.Inject;
import com.haulmont.testtask.model.repository.Repository;
import org.hibernate.exception.ConstraintViolationException;

public class ControllerImpl<T> implements Controller<T> {

    private Repository<T> repository;

    @Inject
    public ControllerImpl(Repository<T> repository) {
        this.repository = repository;
    }

    @Override
    public T add(T entity) {
        return repository.add(entity);
    }

    @Override
    public void update(T entity) {
        repository.update(entity);
    }

    @Override
    public void delete(long id) throws ConstraintViolationException {
        repository.delete(id);
    }
}
