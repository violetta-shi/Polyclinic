package dao;

import model.Person;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface PersonDao {
    int addPerson(Person person) throws SQLException;
    int updatePerson(Person person) throws SQLException;
    int deletePerson(int id);
    List<Person> showPeople();
    Person findPersonById(int id);
}
