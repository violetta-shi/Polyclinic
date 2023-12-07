package services;

import dao.PatientDao;
import interfaces.DAO;
import interfaces.Service;
import model.Doctor;
import model.Patient;
import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PatientService implements Service<Patient> {
    DAO daoService = new PatientDao();
    @Override
    public Patient findEntity(int id) {
        Patient entity = (Patient) daoService.findById(id);
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
        /*List<Patient> newPatients = new ArrayList<>();
        for(Patient patient : patients){
            if(patient.getPerson() != null){
                //patient.getPerson().set(null);
            }
            if(patient.getUser() != null){
                patient.getUser().setDoctor(null);
            }
            patient.add(doctor);
        }
        return newDoctors;*/
        return daoService.findAll();
    }
}
