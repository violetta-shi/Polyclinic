package model;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String login;
    private String password;
    private String role;
    private int personId;

    public User(int userId, String login, String password, String role, int personId) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
        this.personId = personId;
    }

    public User(){
        this.userId = -1;
        this.login = "";
        this.password = "";
        this.role = "";
        this.personId = -1;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }
}
