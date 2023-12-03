package dao;

import model.User;

import java.sql.Statement;
import java.util.List;

public interface UserDao {
    boolean addUser(User user, Statement stmt);
    boolean updateUser(User user, Statement stmt);
    boolean deletUser(int id, Statement stmt);
    List<User> showUsers(Statement stmt);
}
