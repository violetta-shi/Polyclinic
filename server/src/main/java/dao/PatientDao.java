package dao;

import interfaces.DAO;
import model.Doctor;
import model.Patient;
import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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

    public Patient findByUserId(int id) {
        Patient patient = null;
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Patient> cr = cb.createQuery(Patient.class);
            Root<Patient> root = cr.from(Patient.class);
            cr.select(root).where(cb.equal(root.get("user").get("userId"), id));
            patient = session.createQuery(cr).uniqueResult();
            tx.commit();
            session.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

        return patient;
    }
}
