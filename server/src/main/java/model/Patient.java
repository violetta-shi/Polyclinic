package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Patient implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private int patientId;
    @Column(name = "birth_date")
    private String birthDate;
    @Column(name = "passport_id")
    private String passportId;
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "patient_disease",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<Disease> diseases = new HashSet<>();

    public Patient(int patientId, String birthDate, String passportId, Person person, Address address, Set<Disease> diseses) {
        this.patientId = patientId;
        this.birthDate = birthDate;
        this.passportId = passportId;
        this.person = person;
        this.address = address;
        this.diseases = diseses;
    }

    public Patient(){
        this.patientId = -1;
        this.birthDate = "";
        this.passportId = "";
        this.person = null;
        this.address = null;
        this.diseases = null;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Set<Disease> getDiseses() {
        return diseases;
    }

    public void setDiseses(Set<Disease> diseses) {
        this.diseases = diseses;
    }
}
