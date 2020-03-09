package com.haulmont.testtask.model.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "DOCTOR", schema = "PUBLIC", catalog = "PUBLIC")
@NamedQueries({@NamedQuery(name = "Doctor.getAll", query = "FROM DoctorEntity")})
public class DoctorEntity {
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String specialization;
    private Collection<PrescriptionEntity> prescriptions;

    @Id
    @Column(name = "ID")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FIRST_NAME")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "MIDDLE_NAME")
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "SPECIALIZATION")
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @OneToMany(mappedBy = "doctor")
    public Collection<PrescriptionEntity> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Collection<PrescriptionEntity> prescriptionsById) {
        this.prescriptions = prescriptionsById;
    }
}
