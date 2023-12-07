package dao;

import interfaces.DAO;
import model.Admin;
import model.Person;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AdminDao implements DAO<Admin> {
    @Override
    public void save(Admin obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Admin obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(Admin obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public Admin findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Admin admin = session.get(Admin.class, id);
        session.close();
        return admin;
    }
    public Admin findByUserId(int id) {
        Admin admin = null;
        try {
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Admin> cr = cb.createQuery(Admin.class);
            Root<Admin> root = cr.from(Admin.class);
            cr.select(root).where(cb.equal(root.get("user").get("userId"), id));
            admin = session.createQuery(cr).uniqueResult();
            tx.commit();
            session.close();
        } catch (Exception e) { // Обрабатывайте общий Exception
            System.out.println("Exception: " + e);
        }

        return admin;
    }

    @Override
    public List<Admin> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<Admin> admins = (List<Admin>)session.createQuery("From Admin").list();
        session.close();
        return admins;
    }
}
