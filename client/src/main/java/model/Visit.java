package model;

import javax.print.Doc;
import java.io.Serializable;

public class Visit implements Serializable {
    private int visitId;
    private String date;
    private String time;
    private String comment;
    private Doctor doctor;
    private Patient patient;

    public Visit(int visitId, String date, String time, String comment, Doctor doctor, Patient patient) {
        this.visitId = visitId;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Visit(){
        this.visitId = -1;
        this.date = "";
        this.time = "";
        this.comment = "";
        this.doctor = null;
        this.patient = null;
    }

    public int getVisitId() {
        return visitId;
    }

    public void setVisitId(int visitId) {
        this.visitId = visitId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
