package dao.daoImpl;

import connection.JDBC;
import dao.PersonDao;
import model.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PersonDaoImpl implements PersonDao {

    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    int st;//status

    @Override
    public int addPerson(Person person) {
        con = JDBC.connect();
        try {
            String addPerson = "INSERT INTO person(name, last_name, patronymic, phone)" +
                    "VALUES(?, ?, ?, ?)";
            ps = con.prepareStatement(addPerson);
            ps.setString(1, person.getName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getPatronymic());
            ps.setString(4, person.getPhone());
            st = ps.executeUpdate();
        } catch (Exception e) {
            st = -1;
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }

    @Override
    public int updatePerson(Person person)  {
        con = JDBC.connect();
        try {
            String update = "INSERT person set name = ?, last_name = ?, patronymic = ?, phone = ? where id = ?";
            ps = con.prepareStatement(update);
            ps.setString(1, person.getName());
            ps.setString(2, person.getLastName());
            ps.setString(3, person.getPatronymic());
            ps.setString(4, person.getPhone());
            ps.setInt(5, person.getId());
            st = ps.executeUpdate();
        } catch (Exception e) {
            st = -2;
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }

    @Override
    public int deletePerson(int id) {
        con = JDBC.connect();
        try{
            String delete = "DELETE FROM person WHERE id = ?";
            ps = con.prepareStatement(delete);
            ps.setInt(1, id);
            st = ps.executeUpdate();
        }catch (Exception e){
            st = -2;
            e.printStackTrace();
        }
        finally {
            try {
                con.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return st;
    }

    @Override
    public List<Person> showPeople() {
        return null;
    }

    @Override
    public Person findPersonById(int id) {
        return null;
    }
}
