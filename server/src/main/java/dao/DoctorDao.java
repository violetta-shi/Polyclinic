package dao;

import interfaces.DAO;
import model.Admin;
import model.Doctor;
import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DoctorDao implements DAO<Doctor> {
    @Override
    public void save(Doctor obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Doctor obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Doctor obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Doctor findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Doctor doctor = session.get(Doctor.class, id);
        session.close();
        return doctor;
    }

    public Doctor findByUserId(int id) {
        Doctor doctor = null;
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Doctor> cr = cb.createQuery(Doctor.class);
            Root<Doctor> root = cr.from(Doctor.class);
            cr.select(root).where(cb.equal(root.get("user").get("userId"), id));
            doctor = session.createQuery(cr).uniqueResult();
            tx.commit();
            session.close();
        } catch (Exception e) { // Обрабатывайте общий Exception
            System.out.println("Exception: " + e);
        }

        return doctor;
    }

    @Override
    public List<Doctor> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<Doctor> doctorList = (List<Doctor>)session.createQuery("From Doctor").list();
        session.close();
        return doctorList;
    }
}
