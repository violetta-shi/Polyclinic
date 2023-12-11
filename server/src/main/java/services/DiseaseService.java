package services;

import dao.DiseaseDao;
import interfaces.DAO;
import interfaces.Service;
import model.Disease;
import model.Doctor;
import model.Patient;

import java.util.List;
import java.util.Set;

public class DiseaseService implements Service<Disease> {
    DAO daoService = new DiseaseDao();
    @Override
    public Disease findEntity(int id) {
        return null;
    }

    @Override
    public void saveEntity(Disease entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Disease entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Disease entity) {
        daoService.update(entity);
    }

    @Override
    public List<Disease> findAllEntities() {
        List<Disease> diseases = daoService.findAll();
        for (Disease disease : diseases){
            Set<Patient> patientSet = disease.getPatients();
            for (Patient patient : patientSet){
                patient.setDiseases(null);
            }
        }
        return diseases;
    }
}
