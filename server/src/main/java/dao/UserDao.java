package dao;

import connection.JDBC;
import interfaces.DAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utility.HibernateSessionFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements DAO<User> {

    @Override
    public void save(User obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(User obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.saveOrUpdate(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(User obj) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(obj);
        tx1.commit();
        session.close();
    }

    @Override
    public User findById(int id) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public List<User> findAll() {
        Session session =   HibernateSessionFactory.getSessionFactory().openSession();
        List<User> users = (List<User>)session.createQuery("From User").list();
        session.close();
        return users;
    }
}
