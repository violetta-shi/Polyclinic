package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int personId;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "patronymic")
    private String patronymic;
    @Column(name = "phone")
    private String phone;
    @Column(name = "gender")
    private String gender;
    @OneToOne(mappedBy = "person")
    private User user;

    public Person(){
        this.personId = -1;
        this.name = "";
        this.lastName = "";
        this.patronymic = "";
        this.phone = "";
        this.user = null;
        this.gender = "";
    }
    public Person(int id, String name, String lastName, String patronymic, String phone, String gender, User user) {
        this.personId = id;
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.gender = gender;
        this.user = user;
    }
    public Person(String name, String lastName, String patronymic, String phone, String gender, User user, Patient patient) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.gender = gender;
        this.user = user;
    }
    public Person(String name, String lastName, String patronymic, String phone, String gender) {
        this.name = name;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.gender = gender;
    }


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
