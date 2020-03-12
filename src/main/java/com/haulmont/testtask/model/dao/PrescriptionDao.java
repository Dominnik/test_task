package com.haulmont.testtask.model.dao;

import com.google.inject.Inject;
import com.haulmont.testtask.model.entity.PrescriptionEntity;
import org.hibernate.SessionFactory;

import java.util.List;

public class PrescriptionDao extends AbstractDao<PrescriptionEntity> {

    @Inject
    public PrescriptionDao(SessionFactory factory) {
        super(PrescriptionEntity.class, factory);
    }

    @Override
    public List<PrescriptionEntity> getAll() {
        return inSession(session -> {
            return session.createNamedQuery("Prescription.getAll", genericClass).getResultList();
        });
    }
}
