package com.haulmont.testtask.model.dao;

import com.google.inject.Inject;
import com.haulmont.testtask.model.entity.PatientEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public class PatientDao extends AbstractDao<PatientEntity> {

    @Inject
    public PatientDao(SessionFactory factory) {
        super(PatientEntity.class, factory);
    }

    @Override
    public List<PatientEntity> getAll() {
        return inSession(session -> {
            return session.createNamedQuery("Patient.getAll", genericClass).getResultList();
        });
    }
}
