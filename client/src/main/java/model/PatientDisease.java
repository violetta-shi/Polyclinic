package model;

import java.io.Serializable;

public class PatientDisease implements Serializable {
    private int patientId;
    private int diseaseId;
    private String status;

    public PatientDisease(int patientId, int diseaseId, String status) {
        this.patientId = patientId;
        this.diseaseId = diseaseId;
        this.status = status;
    }

    public PatientDisease(){
        this.patientId = -1;
        this.diseaseId = -1;
        this.status = "";
    }
}
