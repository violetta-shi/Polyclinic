package model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
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
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "patient_disease",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "disease_id")
    )
    private Set<Disease> diseases = new HashSet<>();
    @OneToMany(mappedBy = "patient", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Visit> visits;

    public Patient(int patientId, String birthDate, String passportId, User user, Address address, Set<Disease> diseses, List<Visit> visits) {
        this.patientId = patientId;
        this.birthDate = birthDate;
        this.passportId = passportId;
        this.user = user;
        this.address = address;
        this.diseases = diseses;
        this.visits = visits;
    }

    public Patient(){
        this.patientId = -1;
        this.birthDate = "";
        this.passportId = "";
        this.user = null;
        this.address = null;
        this.diseases = null;
        this.visits = null;
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


    public Set<Disease> getDiseses() {
        return diseases;
    }

    public void setDiseses(Set<Disease> diseses) {
        this.diseases = diseses;
    }

    public Set<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(Set<Disease> diseases) {
        this.diseases = diseases;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
