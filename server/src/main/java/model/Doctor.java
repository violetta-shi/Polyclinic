package model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private int doctorId;
    @Column(name = "qualification")
    private String qualification;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "room")
    private String room;
    @Column(name = "schedule")
    private String schedule;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "doctor", fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    private List<Visit> visits;

    public Doctor(int doctorId, String qualification, String specialization, String room, String schedule, User user, List<Visit> visits) {
        this.doctorId = doctorId;
        this.qualification = qualification;
        this.specialization = specialization;
        this.room = room;
        this.schedule = schedule;
        this.user = user;
        this.visits = visits;
    }

    public Doctor(){
        this.doctorId = -1;
        this.qualification = "";
        this.specialization = "";
        this.room = "";
        this.schedule = "";
        this.user = null;
        this.visits = null;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
}
