package com.haulmont.testtask.model.dao;

import org.hibernate.exception.ConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public abstract class AbstractDao<T> implements Dao<T> {

    protected Class<T> genericClass;
    protected EntityManager manager;

    public AbstractDao(Class<T> genericClass, EntityManager manager) {
        this.genericClass = genericClass;
        this.manager = manager;
    }

    @Override
    public T add(T entity) {
        manager.getTransaction().begin();
        T dbEntity = manager.merge(entity);
        manager.getTransaction().commit();
        manager.close();
        return dbEntity;
    }

    @Override
    public void delete(long id) throws ConstraintViolationException {
        manager.getTransaction().begin();
        try {
            T dbEntity = manager.find(genericClass, id);
            if (dbEntity == null) return;
            manager.remove(dbEntity);
            manager.getTransaction().commit();
        } catch (RollbackException e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            } else throw e;
        } finally {
            manager.close();
        }
    }

    @Override
    public void update(T entity) {
        manager.getTransaction().begin();
        manager.merge(entity);
        manager.getTransaction().commit();
        manager.close();
    }

    @Override
    public T get(long id) {
        T result = manager.find(genericClass, id);
        manager.close();
        return result;
    }
}
