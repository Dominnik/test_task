package com.haulmont.testtask.controller.module;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.haulmont.testtask.controller.Controller;
import com.haulmont.testtask.controller.ControllerImpl;
import com.haulmont.testtask.model.entity.DoctorEntity;
import com.haulmont.testtask.model.entity.PatientEntity;
import com.haulmont.testtask.model.entity.PrescriptionEntity;
import com.haulmont.testtask.model.module.ModelModule;

public class ControllerModule extends AbstractModule {

    @Override
    public void configure() {
        install(new ModelModule());

        bind(new TypeLiteral<Controller<DoctorEntity>>() {}).to(new TypeLiteral<ControllerImpl<DoctorEntity>>() {}).in(Singleton.class);
        bind(new TypeLiteral<Controller<PatientEntity>>() {}).to(new TypeLiteral<ControllerImpl<PatientEntity>>() {}).in(Singleton.class);
        bind(new TypeLiteral<Controller<PrescriptionEntity>>() {}).to(new TypeLiteral<ControllerImpl<PrescriptionEntity>>() {}).in(Singleton.class);
    }
}
