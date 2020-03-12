package com.haulmont.testtask.model.repository;

import com.google.inject.Inject;
import com.haulmont.testtask.model.dao.Dao;
import com.haulmont.testtask.model.entity.Dto;
import org.hibernate.exception.ConstraintViolationException;

import java.util.ArrayList;
import java.util.List;

public class RepositoryImpl<T extends Dto> implements Repository<T> {

    private Dao<T> dao;
    private List<UpdateEntityListener> listeners;

    @Inject
    public RepositoryImpl(Dao<T> dao) {
        this.dao = dao;
        listeners = new ArrayList<>();
    }

    @Override
    public void addUpdateListener(UpdateEntityListener listener) {
        listeners.add(listener);
    }

    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    @Override
    public T get(long id) {
        return dao.get(id);
    }

    @Override
    public void add(T entity) {
        dao.add(entity);
        listeners.forEach(UpdateEntityListener::onUpdated);
    }

    @Override
    public void update(T entity) {
        dao.update(entity);
        listeners.forEach(UpdateEntityListener::onUpdated);
    }

    @Override
    public void delete(long id) throws ConstraintViolationException {
        dao.delete(id);
        listeners.forEach(UpdateEntityListener::onUpdated);
    }
}
