package services;

import dao.PatientDao;
import interfaces.DAO;
import interfaces.Service;
import model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PatientService implements Service<Patient> {
    DAO daoService = new PatientDao();
    PatientDao patientDao = new PatientDao();
    @Override
    public Patient findEntity(int id) {
        Patient entity = (Patient) daoService.findById(id);
        if(entity.getUser().getPerson() != null){
            entity.getUser().getPerson().setUser(null);
        }
        if(entity.getUser() != null){
            entity.getUser().setDoctor(null);
        }
        return entity;
    }

    @Override
    public void saveEntity(Patient entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Patient entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Patient entity) {
        daoService.update(entity);
    }

    @Override
    public List<Patient> findAllEntities() {
        List<Patient> patients = daoService.findAll();
        List<Patient> newPatients = new ArrayList<>();
        for(Patient patient: patients){
            if(patient.getUser().getPerson() != null){
                patient.getUser().getPerson().setUser(null);
            }
            if(patient.getDiseses() != null) {
                Set<Disease> diseases = new HashSet<>();
                for (Disease disease : patient.getDiseses()) {
                    disease.setPatients(null);
                    diseases.add(disease);
                }
                patient.setDiseases(diseases);
            }
            if(patient.getUser() != null){
                patient.getUser().setPatient(null);
            }
            if(patient.getAddress() != null){
                patient.getAddress().setPatients(null);
            }
            for(Visit visit : patient.getVisits()){
                List<Visit> visits = new ArrayList<>();
                if(visit != null){
                    visit.setPatient(null);
                    visit.setDoctor(null);
                    visits.add(visit);
                }
                patient.setVisits(visits);
            }
            newPatients.add(patient);
        }
        return newPatients;
    }

    public Patient findEntityByUserId(User entity){
       Patient patient = patientDao.findByUserId(entity.getUserId());
        if(patient.getUser().getPerson() != null){
            patient.getUser().getPerson().setUser(null);
        }
        if(patient.getUser() != null){
            patient.getUser().setPatient(null);
        }
        if(patient.getDiseses()!= null){
            for(Disease disease : patient.getDiseases()){
                disease.setPatients(null);
            }
        }
        if(patient.getAddress() != null){
            patient.getAddress().setPatients(null);
        }
        for(Visit visit : patient.getVisits()){
            if(visit != null){
                visit.setPatient(null);
                visit.setDoctor(null);
            }
        }
        return patient;
    }
}
