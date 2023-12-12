package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Doctor implements Serializable {
    private int doctorId;
    private String qualification;
    private String specialization;
    private String room;
    private String schedule;
    private User user;
    private Set<Visit> visits = new HashSet<>();

    public Doctor(int doctorId, String qualification, String specialization, String room, String schedule, User user, Set<Visit> visits) {
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

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }

    @Override
    public String toString() {
        return
                doctorId + " " + specialization + ':' + user.getPerson().getLastName() +
                ' ';
    }
}
