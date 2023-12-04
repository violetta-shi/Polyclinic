package services;

import dao.PersonDao;
import dao.UserDao;
import interfaces.DAO;
import interfaces.Service;
import model.Person;
import model.User;

import java.util.List;

public class PersonService implements Service<Person> {

    DAO daoService = new PersonDao();
    @Override
    public Person findEntity(int id) {
        Person entity = (Person) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Person entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Person entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Person entity)  {
        daoService.update(entity);
    }

    @Override
    public List<Person> findAllEntities() {
        return daoService.findAll();
    }
}
