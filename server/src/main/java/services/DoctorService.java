package services;

import dao.DoctorDao;
import interfaces.DAO;
import interfaces.Service;
import model.*;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class DoctorService implements Service<Doctor> {
    DAO daoService = new DoctorDao();
    DoctorDao doctorDao = new DoctorDao();
    @Override
    public Doctor findEntity(int id) {
        Doctor entity = (Doctor) daoService.findById(id);
        if(entity.getUser().getPerson() != null){
            entity.getUser().getPerson().setUser(null);
        }
        if(entity.getUser() != null){
            entity.getUser().setDoctor(null);
        }
        if(entity.getVisits() != null){
            List<Visit> visits = new ArrayList<>();
            for(Visit visit : entity.getVisits()){
                visit.setDoctor(null);
                visit.setPatient(null);
                visits.add(visit);
            }
            entity.setVisits(visits);
        }
        return entity;
    }

    @Override
    public void saveEntity(Doctor entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Doctor entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Doctor entity) {
        daoService.update(entity);
    }

    @Override
    public List<Doctor> findAllEntities() {
        List<Doctor> doctors = daoService.findAll();
        List<Doctor> newDoctors = new ArrayList<>();
        for(Doctor doctor : doctors){
            if(doctor.getUser().getPerson() != null){
                doctor.getUser().getPerson().setUser(null);
            }
            if(doctor.getUser() != null){
                doctor.getUser().setDoctor(null);
            }
            if(doctor.getVisits() != null){
                List<Visit> visits = new ArrayList<>();
                for(Visit visit : doctor.getVisits()){
                    visit.setDoctor(null);
                    visit.setPatient(null);
                    visits.add(visit);
                }
                doctor.setVisits(visits);
            }
            newDoctors.add(doctor);
        }
        return newDoctors;
    }

    public Doctor findEntityByUserId(User entity){
        Doctor doctor = doctorDao.findByUserId(entity.getUserId());
        if(doctor.getUser().getPerson() != null){
            doctor.getUser().getPerson().setUser(null);
        }
        if(doctor.getUser() != null){
            doctor.getUser().setDoctor(null);
        }
        if(doctor.getVisits() != null){
            List<Visit> visits = new ArrayList<>();
            for(Visit visit : doctor.getVisits()){
                visit.setDoctor(null);
                visit.setPatient(null);
                visits.add(visit);
            }
            doctor.setVisits(visits);
        }
        return doctor;
    }
}
