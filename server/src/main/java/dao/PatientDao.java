package dao;

import interfaces.DAO;
import model.Patient;
import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import java.util.List;

public class PatientDao implements DAO<Patient> {
    @Override
    public void save(Patient obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Patient obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Patient obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Patient findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Patient patient = session.get(Patient.class, id);
        session.close();
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<Patient> patientList = (List<Patient>)session.createQuery("From Patient").list();
        session.close();
        return patientList;
    }
}
