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

    public Visit(int visitId, String date, String time, String comment, int doctorId, int patientId) {
        this.visitId = visitId;
        this.date = date;
        this.time = time;
        this.comment = comment;

    }

    public Visit(){
        this.visitId = -1;
        this.date = "";
        this.time = "";
        this.comment = "";

    }
}
