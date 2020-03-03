package com.haulmont.testtask.service;

import com.haulmont.testtask.model.Patient;

import java.util.List;

public interface PatientService {

    List<Patient> findAll();

    Patient findById(Long id);

    void save(Patient item);

    void delete(Long id);

    boolean canDelete(Long id);
}
