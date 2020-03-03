package com.haulmont.testtask.repository;

import com.haulmont.testtask.model.Patient;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query(value = "SELECT id FROM prescription as pr WHERE pr.patient_id = :id",nativeQuery = true)
    List<Long> canDelete(@Param("id")Long id);
}
