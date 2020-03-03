package com.haulmont.testtask.repository;


import com.haulmont.testtask.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "SELECT id FROM prescription as pr WHERE pr.doctor_id = :id", nativeQuery = true)
    List<Long> canDelete(@Param("id")Long id);
}
