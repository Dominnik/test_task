package com.haulmont.testtask.model.dao;

import com.google.inject.Inject;
import com.haulmont.testtask.model.entity.PatientEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PatientDao extends AbstractDao<PatientEntity> {

    @Inject
    public PatientDao(EntityManager manager) {
        super(PatientEntity.class, manager);
    }

    @Override
    public List<PatientEntity> getAll() {
        TypedQuery<PatientEntity> namedQuery = manager.createNamedQuery("Patient.getAll", genericClass);
        List<PatientEntity> result = namedQuery.getResultList();
        manager.close();
        return result;
    }
}
