package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;
    @Column(name = "city")
    private String city;
    @Column(name = "district")
    private String district;
    @Column(name = "street")
    private String street;
    @Column(name = "house_number")
    private String houseNumber;
    @Column(name = "apartment_number")
    private int apartmentNumber;
    @OneToMany(mappedBy = "address")
    private List<Patient> patients;

    public Address(int addressId, String city, String district, String street, String houseNumber, int apartamentNumber) {
        this.addressId = addressId;
        this.city = city;
        this.district = district;
        this.street = street;
        this.houseNumber = houseNumber;
        this.apartmentNumber = apartamentNumber;
    }

    public Address(){
        this.addressId = -1;
        this.city = "";
        this.district = "";
        this.street = "";
        this.houseNumber = "";
        this.apartmentNumber = -1;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
