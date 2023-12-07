package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "visit")
public class Visit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "visit_id")
    private int visitId;
    @Column(name = "date")
    private String date;
    @Column(name = "time")
    private String time;
    @Column(name = "comment")
    private String comment;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
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
        this.patient = null;
        this.doctor = null;
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
