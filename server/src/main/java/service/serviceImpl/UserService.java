package service.serviceImpl;

import dao.UserDao;
import dao.daoImpl.UserDaoImpl;
import model.User;
import service.Service;

import java.util.List;

public class UserService implements Service<User> {

    UserDao daoService = new UserDaoImpl();
    @Override
    public User findEntity(int id) {

        return null;
    }

    @Override
    public void saveEntity(User entity) {
        daoService.addUser(entity);
    }

    @Override
    public void deleteEntity(User entity) {

    }

    @Override
    public void updateEntity(User entity) {

    }

    @Override
    public List<User> findAllEntities() {
        return null;
    }
}
