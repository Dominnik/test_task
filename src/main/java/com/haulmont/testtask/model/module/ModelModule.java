package com.haulmont.testtask.model.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.haulmont.testtask.model.dao.Dao;
import com.haulmont.testtask.model.dao.DoctorDao;
import com.haulmont.testtask.model.dao.PatientDao;
import com.haulmont.testtask.model.dao.PrescriptionDao;
import com.haulmont.testtask.model.entity.DoctorEntity;
import com.haulmont.testtask.model.entity.PatientEntity;
import com.haulmont.testtask.model.entity.PrescriptionEntity;
import com.haulmont.testtask.model.repository.Repository;
import com.haulmont.testtask.model.repository.RepositoryImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ModelModule extends AbstractModule {

    @Override
    public void configure() {
        bind(new TypeLiteral<Dao<DoctorEntity>>() {}).to(DoctorDao.class).in(Singleton.class);
        bind(new TypeLiteral<Dao<PatientEntity>>() {}).to(PatientDao.class).in(Singleton.class);
        bind(new TypeLiteral<Dao<PrescriptionEntity>>() {}).to(PrescriptionDao.class).in(Singleton.class);
        bind(new TypeLiteral<Repository<DoctorEntity>>() {}).to(new TypeLiteral<RepositoryImpl<DoctorEntity>>() {}).in(Singleton.class);
        bind(new TypeLiteral<Repository<PatientEntity>>() {}).to(new TypeLiteral<RepositoryImpl<PatientEntity>>() {}).in(Singleton.class);
        bind(new TypeLiteral<Repository<PrescriptionEntity>>() {}).to(new TypeLiteral<RepositoryImpl<PrescriptionEntity>>() {}).in(Singleton.class);
    }

    @Provides
    @Singleton
    public SessionFactory provideSessionFactory() {
        return new Configuration().configure().buildSessionFactory();
    }

}
