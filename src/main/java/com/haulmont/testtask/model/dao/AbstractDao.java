package com.haulmont.testtask.model.dao;

import com.haulmont.testtask.model.entity.Dto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractDao<T extends Dto> implements Dao<T> {

    protected Class<T> genericClass;
    protected SessionFactory factory;

    public AbstractDao(Class<T> genericClass, SessionFactory factory) {
        this.genericClass = genericClass;
        this.factory = factory;
    }

    @Override
    public void add(T entity) {
        inSession(session -> {
            session.getTransaction().begin();
            try {
                session.save(entity);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        });
    }

    @Override
    public void delete(long id) {
        inSession(session -> {
            T dbEntity = get(id);
            if (dbEntity == null) return;
            session.getTransaction().begin();
            try {
                session.delete(dbEntity);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        });
    }

    @Override
    public void update(T entity) {
        inSession(session -> {
            session.getTransaction().begin();
            try {
                session.merge(entity);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
            }
        });
    }

    @Override
    public T get(long id) {
        return inSession(session -> {
            return session.find(genericClass, id);
        });
    }

    protected void inSession(Consumer<Session> consumer) {
        Session session = factory.openSession();
        consumer.accept(session);
        session.close();
    }

    protected <R> R inSession(Function<Session, R> consumer) {
        Session session = factory.openSession();
        R result = consumer.apply(session);
        session.close();
        return result;
    }
}
