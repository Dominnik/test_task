package com.haulmont.testtask.view.page.main;

import com.haulmont.testtask.view.core.Page;
import com.haulmont.testtask.view.page.doctor.DoctorView;
import com.haulmont.testtask.view.page.patient.PatientView;
import com.haulmont.testtask.view.page.prescription.PrescriptionView;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

@Theme(ValoTheme.THEME_NAME)
public class MainUI extends UI {

    private static final String TITLE = "Поликлиника";

    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle(TITLE);

        Navigator navigator = new Navigator(this, this);
        navigator.addView(Page.DOCTORS.getUrl(), new DoctorView());
        navigator.addView(Page.PATIENTS.getUrl(), new PatientView());
        navigator.addView(Page.PRESCRIPTIONS.getUrl(), new PrescriptionView());
        navigator.navigateTo(Page.DOCTORS.getUrl());
    }
}