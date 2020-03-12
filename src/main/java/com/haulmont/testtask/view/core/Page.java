package com.haulmont.testtask.view.core;

public enum Page {
    DOCTORS("doctors", "Врачи"),
    PATIENTS("patients", "Пациенты"),
    PRESCRIPTIONS("prescriptions", "Рецепты");

    private String url;
    private String title;

    Page(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }
}