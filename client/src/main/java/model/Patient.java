package model;

import java.io.Serializable;

public class Patient implements Serializable {
    private int patientId;
    private String birthDate;
    private String passportId;
    private Person person;
    private Address address;

    public Patient(int patientId, String birthDate, String passportId, int personId, int addressId, Person person, Address address) {
        this.patientId = patientId;
        this.birthDate = birthDate;
        this.passportId = passportId;
        this.person = person;
        this.address = address;
    }

    public Patient(){
        this.patientId = -1;
        this.birthDate = "";
        this.passportId = "";
        this.person = null;
        this.address = null;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
