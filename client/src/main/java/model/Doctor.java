package model;

import java.io.Serializable;

public class Doctor implements Serializable {
    private int doctorId;
    private String qualification;
    private String specialization;
    private String room;
    private String schedule;
    private int userId;

    public Doctor(int doctorId, String qualification, String specialization, String room, String schedule, int userId) {
        this.doctorId = doctorId;
        this.qualification = qualification;
        this.specialization = specialization;
        this.room = room;
        this.schedule = schedule;
        this.userId = userId;
    }

    public Doctor(){
        this.doctorId = -1;
        this.qualification = "";
        this.specialization = "";
        this.room = "";
        this.schedule = "";
        this.userId = -1;
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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
