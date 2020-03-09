package com.haulmont.testtask.model.dao;

import com.google.inject.Inject;
import com.haulmont.testtask.model.entity.DoctorEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class DoctorDao extends AbstractDao<DoctorEntity> {

    @Inject
    public DoctorDao(EntityManager manager) {
        super(DoctorEntity.class, manager);
    }

    @Override
    public List<DoctorEntity> getAll() {
        TypedQuery<DoctorEntity> namedQuery = manager.createNamedQuery("Doctor.getAll", genericClass);
        List<DoctorEntity> result = namedQuery.getResultList();
        manager.close();
        return result;
    }
}
