package com.haulmont.testtask.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NamedQueries({@NamedQuery(name = "Patient.getAll", query = "FROM PatientEntity")})
@Table(name = "PATIENT", schema = "PUBLIC", catalog = "PUBLIC")
public class PatientEntity implements Dto {
    private long id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String phoneNumber;

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
    @Column(name = "PHONE_NUMBER")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientEntity that = (PatientEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, middleName, firstName, middleName, phoneNumber);
    }

    @Override
    public String toString() {
        return String.join(" ", lastName, firstName, middleName == null ? "" : middleName);
    }
}
