package services;

import interfaces.DAO;
import interfaces.Service;
import model.User;
import dao.UserDao;
import java.util.List;

public class UserService implements Service<User> {
    DAO daoService = new UserDao();
    @Override
    public User findEntity(int id) {
        User entity = (User) daoService.findById(id);
        if (entity.getPerson() != null) {
            entity.getPerson().setUser(null);
        }
        if (entity.getDoctor() != null) {
            entity.getDoctor().setUser(null);
        }
        if (entity.getAdmin() != null) {
            entity.getAdmin().setUser(null);
        }
        if (entity.getPatient() != null) {
            entity.getPatient().setUser(null);
        }
        return entity;
    }

    @Override
    public void saveEntity(User entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(User entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(User entity) {
        daoService.update(entity);
    }

    @Override
    public List<User> findAllEntities() {
        return daoService.findAll();
    }
}
