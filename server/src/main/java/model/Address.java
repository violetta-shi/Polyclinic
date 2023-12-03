package model;

import java.io.Serializable;

public class Address implements Serializable {
    private int addressId;
    private String city;
    private String district;
    private String street;
    private String houseNumber;
    private int apartmentNumber;

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
}
