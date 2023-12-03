package model;

import java.io.Serializable;

public class Disease implements Serializable {
    private int diseaseId;
    private String name;
    private String symptoms;
    private String status;
    private String treatment;

    public Disease(int diseaseId, String name, String symptoms, String status, String treatment) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.symptoms = symptoms;
        this.status = status;
        this.treatment = treatment;
    }

    public Disease(){
        this.diseaseId = -1;
        this.name = "";
        this.symptoms = "";
        this.status = "";
        this.treatment = "";
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}
