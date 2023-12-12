package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Patient implements Serializable {
    private int patientId;
    private String birthDate;
    private String passportId;
    private User user;
    private Address address;
    private Set<Disease> diseases = new HashSet<>();

    public Patient(int patientId, String birthDate, String passportId, User user, Address address, Set<Disease> diseases) {
        this.patientId = patientId;
        this.birthDate = birthDate;
        this.passportId = passportId;
        this.user = user;
        this.address = address;
        this.diseases = diseases;
    }

    public Patient(String birthDate, String passportId, User user, Address address) {
        this.birthDate = birthDate;
        this.passportId = passportId;
        this.user = user;
        this.address = address;
    }

    public Patient(){
        this.patientId = -1;
        this.birthDate = "";
        this.passportId = "";
        this.user = null;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }
}
