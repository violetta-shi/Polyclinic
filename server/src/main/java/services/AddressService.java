package services;

import dao.AddressDao;
import interfaces.DAO;
import interfaces.Service;
import model.Address;
import model.Person;

import java.util.List;

public class AddressService implements Service<Address> {
    DAO daoService = new AddressDao();
    @Override
    public Address findEntity(int id) {
        Address entity = (Address) daoService.findById(id);
        return entity;
    }

    @Override
    public void saveEntity(Address entity) {
        daoService.save(entity);
    }

    @Override
    public void deleteEntity(Address entity) {
        daoService.delete(entity);
    }

    @Override
    public void updateEntity(Address entity) {
        daoService.update(entity);
    }

    @Override
    public List<Address> findAllEntities() {
        return daoService.findAll();
    }
}
