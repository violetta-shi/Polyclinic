package services;

import dao.VisitDao;
import interfaces.DAO;
import interfaces.Service;
import model.Disease;
import model.Doctor;
import model.Visit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VisitService implements Service<Visit> {
    DAO daoService = new VisitDao();

    @Override
    public Visit findEntity(int id) {
        Visit entity =  (Visit) daoService.findById(id);
        if(entity.getDoctor() != null){
            if(entity.getDoctor().getUser() != null){
                entity.getDoctor().getUser().setDoctor(null);
            }
            entity.getDoctor().setVisits(null);
        }
        if(entity.getPatient() != null){
            if(entity.getPatient().getUser() != null){
                entity.getPatient().getUser().setPatient(null);
            }
            entity.getPatient().setVisits(null);
        }
        return entity;
    }

    @Override
    public void saveEntity(Visit entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Visit entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Visit entity) {
        daoService.update(entity);
    }

    @Override
    public List<Visit> findAllEntities() {
        List<Visit> visits = daoService.findAll();
        List<Visit> newVisit = new ArrayList<>();
        for(Visit visit : visits){
            if(visit.getPatient() != null){
                if(visit.getPatient().getDiseses() != null){
                    Set<Disease> diseases = new HashSet<>();
                    for (Disease disease : visit.getPatient().getDiseses()) {
                        disease.setPatients(null);
                        diseases.add(disease);
                    }
                    visit.getPatient().setDiseases(diseases);
                }
                if(visit.getPatient().getUser() != null){
                    visit.getPatient() .getUser().setPatient(null);
                    visit.getPatient().getUser().getPerson().setUser(null);
                    visit.getPatient().setVisits(null);
                }
                if(visit.getPatient() .getAddress() != null){
                    visit.getPatient() .getAddress().setPatients(null);
                }
            }
            if(visit.getDoctor() != null){
                if(visit.getDoctor().getUser() != null){
                    visit.getDoctor().getUser().setDoctor(null);
                    visit.getDoctor().getUser().getPerson().setUser(null);
                    visit.getDoctor().setVisits(null);
                }
            }
            newVisit.add(visit);
        }
        return newVisit;
    }
}
