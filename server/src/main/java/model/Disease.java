package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "disease")
public class Disease implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disease_id")
    private int diseaseId;
    @Column(name = "name")
    private String name;
    @Column(name = "symptoms")
    private String symptoms;
    @Column(name = "treatment")
    private String treatment;
    @ManyToMany(mappedBy = "diseases", fetch = FetchType.EAGER)
    private Set<Patient> patients = new HashSet<>();


    public Disease(int diseaseId, String name, String symptoms, String treatment, Set<Patient> patients) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.symptoms = symptoms;
        this.treatment = treatment;
        this.patients = patients;
    }

    public Disease(){
        this.diseaseId = -1;
        this.name = "";
        this.symptoms = "";
        this.treatment = "";
        this.patients = null;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }
}
