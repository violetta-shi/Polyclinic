package model;

import java.io.Serializable;

public class PatientDisease implements Serializable {
    private int patientId;
    private int diseaseId;

    public PatientDisease(int patientId, int diseaseId) {
        this.patientId = patientId;
        this.diseaseId = diseaseId;
    }

    public PatientDisease(){
        this.patientId = -1;
        this.diseaseId = -1;
    }
}
