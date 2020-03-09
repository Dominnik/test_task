package com.haulmont.testtask.view.main;

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
        /*navigator.addView(Page.DOCTORS.getUrl(), new DoctorView());
        navigator.addView(Page.PATIENTS.getUrl(), new PatientView());
        navigator.addView(Page.PRESCRIPTIONS.getUrl(), new PrescriptionView());*/
        navigator.navigateTo(Page.DOCTORS.getUrl());
    }

    public enum Page {
        DOCTORS("doctors"),
        PATIENTS("patients"),
        PRESCRIPTIONS("prescriptions");

        private String url;

        Page(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }
}