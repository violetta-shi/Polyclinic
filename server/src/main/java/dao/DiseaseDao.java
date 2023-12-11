package dao;

import interfaces.DAO;
import model.Disease;
import model.Doctor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import java.util.List;

public class DiseaseDao  implements DAO<Disease> {
    @Override
    public void save(Disease obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Disease obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Disease obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Disease findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Disease disease = session.get(Disease.class, id);
        session.close();
        return disease;
    }

    @Override
    public List<Disease> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<Disease> doctorList = (List<Disease>)session.createQuery("From Disease").list();
        session.close();
        return doctorList;
    }
}
