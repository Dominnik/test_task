package com.haulmont.testtask.model.dao;

import com.google.inject.Inject;
import com.haulmont.testtask.model.entity.PrescriptionEntity;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class PrescriptionDao extends AbstractDao<PrescriptionEntity> {

    @Inject
    public PrescriptionDao(EntityManager manager) {
        super(PrescriptionEntity.class, manager);
    }

    @Override
    public List<PrescriptionEntity> getAll() {
        TypedQuery<PrescriptionEntity> namedQuery = manager.createNamedQuery("Prescription.getAll", genericClass);
        List<PrescriptionEntity> result = namedQuery.getResultList();
        manager.close();
        return result;
    }
}
