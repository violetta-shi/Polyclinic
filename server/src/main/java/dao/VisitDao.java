package dao;

import interfaces.DAO;
import model.Patient;
import model.Visit;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import java.util.List;

public class VisitDao implements DAO<Visit> {
    @Override
    public void save(Visit obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Visit obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Visit obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Visit findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Visit visit = session.get(Visit.class, id);
        session.close();
        return visit;
    }

    @Override
    public List<Visit> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<Visit> visittList = (List<Visit>)session.createQuery("From Visit").list();
        session.close();
        return visittList;
    }
}
