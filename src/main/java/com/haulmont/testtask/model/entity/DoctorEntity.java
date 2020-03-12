package com.haulmont.testtask.model.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "DOCTOR", schema = "PUBLIC", catalog = "PUBLIC")
@NamedQueries({@NamedQuery(name = "Doctor.getAll", query = "FROM DoctorEntity")})
public class DoctorEntity implements Dto {
    private long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String specialization;
    private Collection<PrescriptionEntity> prescriptions;

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
    @Column(name = "LAST_NAME")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    public Collection<PrescriptionEntity> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(Collection<PrescriptionEntity> prescriptionsById) {
        this.prescriptions = prescriptionsById;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorEntity that = (DoctorEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(specialization, that.specialization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, middleName, firstName, middleName, specialization);
    }

    @Override
    public String toString() {
        return String.join(" ", lastName, firstName, middleName == null ? "" : middleName);
    }
}
