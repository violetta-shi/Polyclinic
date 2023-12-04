package dao;

import interfaces.DAO;
import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import java.util.List;

public class PersonDao implements DAO<Person> {
    @Override
    public void save(Person obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Person obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Person obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Person findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Person personData = session.get(Person.class, id);
        session.close();
        return personData;
    }

    @Override
    public List<Person> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<Person> personData = (List<Person>)session.createQuery("From Person").list();
        session.close();
        return personData;
    }
}
