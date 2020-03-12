package com.haulmont.testtask.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "PRESCRIPTION", schema = "PUBLIC", catalog = "PUBLIC")
@NamedQueries({@NamedQuery(name = "Prescription.getAll", query = "FROM PrescriptionEntity")})
public class PrescriptionEntity implements Dto {
    private long id;
    private String title;
    private Date creationDate;
    private short validity;
    private Priority priority;
    private PatientEntity patient;
    private DoctorEntity doctor;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TITLE")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "CREATION_DATE")
    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Basic
    @Column(name = "VALIDITY")
    public short getValidity() {
        return validity;
    }

    public void setValidity(short validity) {
        this.validity = validity;
    }

    @Basic
    @Column(name = "PRIORITY")
    @Enumerated(EnumType.STRING)
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @ManyToOne
    @JoinColumn(name = "PATIENT_ID", referencedColumnName = "ID", nullable = false)
    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patientByPatientId) {
        this.patient = patientByPatientId;
    }

    @ManyToOne
    @JoinColumn(name = "DOCTOR_ID", referencedColumnName = "ID", nullable = false)
    public DoctorEntity getDoctor() {
        return doctor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionEntity that = (PrescriptionEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(title, that.title) &&
                Objects.equals(creationDate, that.creationDate) &&
                Objects.equals(validity, that.validity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, creationDate, validity);
    }

    public void setDoctor(DoctorEntity doctorByDoctorId) {
        this.doctor = doctorByDoctorId;
    }
}
