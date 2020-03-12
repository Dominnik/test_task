package com.haulmont.testtask.model.dao;

import com.google.inject.Inject;
import com.haulmont.testtask.model.entity.DoctorEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public class DoctorDao extends AbstractDao<DoctorEntity> {

    @Inject
    public DoctorDao(SessionFactory factory) {
        super(DoctorEntity.class, factory);
    }

    @Override
    public List<DoctorEntity> getAll() {
        return inSession(session -> {
            return session.createQuery("FROM DoctorEntity", DoctorEntity.class).getResultList();
        });
    }
}
