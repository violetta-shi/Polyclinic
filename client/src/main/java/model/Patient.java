package model;

import java.io.Serializable;

public class Patient implements Serializable {
    private int patientId;
    private String birthDate;
    private String passportId;
    private int personId;
    private int addressId;

    public Patient(int patientId, String birthDate, String passportId, int personId, int addressId) {
        this.patientId = patientId;
        this.birthDate = birthDate;
        this.passportId = passportId;
        this.personId = personId;
        this.addressId = addressId;
    }

    public Patient(){
        this.patientId = -1;
        this.birthDate = "";
        this.passportId = "";
        this.personId = -1;
        this.addressId = -1;
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

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
