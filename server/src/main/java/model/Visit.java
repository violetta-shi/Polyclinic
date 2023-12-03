package model;

import java.io.Serializable;

public class Visit implements Serializable {
    private int visitId;
    private String date;
    private String time;
    private String comment;
    private int doctorId;
    private int patientId;

    public Visit(int visitId, String date, String time, String comment, int doctorId, int patientId) {
        this.visitId = visitId;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }

    public Visit(){
        this.visitId = -1;
        this.date = "";
        this.time = "";
        this.comment = "";
        this.doctorId = -1;
        this.patientId = -1;
    }
}
