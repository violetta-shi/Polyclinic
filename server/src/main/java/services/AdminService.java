package services;

import dao.AdminDao;
import dao.DoctorDao;
import interfaces.DAO;
import interfaces.Service;
import model.Admin;
import model.Person;
import model.User;

import java.util.List;

public class AdminService implements Service<Admin> {
    DAO daoService = new AdminDao();
    AdminDao adminDao = new AdminDao();
    @Override
    public Admin findEntity(int id) {
        Admin entity = (Admin) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Admin entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Admin entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Admin entity) {
        daoService.update(entity);
    }

    @Override
    public List<Admin> findAllEntities() {
        return daoService.findAll();
    }
    public Admin findEntityByUserId(User entity){
        Admin admin = adminDao.findByUserId(entity.getUserId());
        if(admin.getUser().getPerson() != null){
            admin.getUser().getPerson().setUser(null);
        }
        if (admin.getUser() != null) {
            admin.getUser().setAdmin(null);
        }

        return admin;
    }
}
