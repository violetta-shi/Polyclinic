package dao;

import interfaces.DAO;
import model.Address;
import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import java.util.List;

public class AddressDao implements DAO<Address> {
    @Override
    public void save(Address obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Address obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Address obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Address findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Address address = session.get(Address.class, id);
        session.close();
        return address;
    }

    @Override
    public List<Address> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<Address> addresses = (List<Address>)session.createQuery("From Address").list();
        session.close();
        return addresses;
    }
}
