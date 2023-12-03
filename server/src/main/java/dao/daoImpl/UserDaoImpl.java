package dao.daoImpl;

import dao.UserDao;
import model.User;

import java.sql.Statement;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean addUser(User user, Statement stmt) {

        /*String addUser = "INSERT INTO person(login, last_name, patronymic, phone)" +
                "VALUES ('" + user.getLogin() ?, ?, ?)";*/

        /*String  addUser = "INSERT INTO user (authorID, isbn)" + "VALUES (" + i + ", '"
                + isbn[i - 1] + "')";*/

        return false;
    }

    @Override
    public boolean updateUser(User user, Statement stmt) {
        return false;
    }

    @Override
    public boolean deletUser(int id, Statement stmt) {
        return false;
    }

    @Override
    public List<User> showUsers(Statement stmt) {
        return null;
    }
}
