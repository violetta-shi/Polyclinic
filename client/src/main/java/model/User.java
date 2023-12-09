package model;

import enums.Roles;

import java.io.Serializable;

public class User implements Serializable {
    private int userId;
    private String login;
    private String password;
    private String role;
    private String workPhone;
    private Person person;

    public User(int userId, String login, String password, String role, String workPhone, Person person) {
        this.userId = userId;
        this.login = login;
        this.password = password;
        this.role = role;
        this.workPhone = workPhone;
        this.person = person;
    }

    public User(String login, String password, String role, String workPhone, Person person) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.workPhone = workPhone;
        this.person = person;
    }
    public User(){
        this.userId = -1;
        this.login = "";
        this.password = "";
        this.role = "";
        this.person = null;
        this.workPhone = "";
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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

    public void setRole(Roles role) {
        this.role = role.toString();
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }
}
