package model;

import java.io.Serializable;

public class Doctor implements Serializable {
    private int doctorId;
    private String qualification;
    private String specialization;
    private String room;
    private String schedule;
    private User user;

    public Doctor(int doctorId, String qualification, String specialization, String room, String schedule, User user) {
        this.doctorId = doctorId;
        this.qualification = qualification;
        this.specialization = specialization;
        this.room = room;
        this.schedule = schedule;
        this.user = user;
    }

    public Doctor(){
        this.doctorId = -1;
        this.qualification = "";
        this.specialization = "";
        this.room = "";
        this.schedule = "";
        this.user = null;
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
}
