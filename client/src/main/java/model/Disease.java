package model;

import java.io.Serializable;

public class Disease implements Serializable {
    private int diseaseId;
    private String name;
    private String symptoms;
    private String treatment;

    public Disease(int diseaseId, String name, String symptoms, String treatment) {
        this.diseaseId = diseaseId;
        this.name = name;
        this.symptoms = symptoms;
        this.treatment = treatment;
    }

    public Disease(){
        this.diseaseId = -1;
        this.name = "";
        this.symptoms = "";
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


    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    @Override
    public String toString() {
        return
                diseaseId +
                " " + name;
    }
}
