package model;

import java.io.Serializable;

public class Person implements Serializable {
    private int personId;
    private String name;
    private String lastName;
    private String patronymic;
    private String phone;

    public Person(){
        this.personId = -1;
        this.name = "";
        this.lastName = "";
        this.patronymic = "";
        this.phone = "";
    }
    public Person(int id, String name, String lastName, String patronymic, String phone) {
        this.personId = id;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
    }

    public void setId(int id) {
        this.personId = id;
    }
    public String getName() {
        return name;
    }

    public int getId() {
        return personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
