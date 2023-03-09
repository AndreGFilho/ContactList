package com.andrefilho.contactList.persistence.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
public class Contact extends AbstractModel{

    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private String address;
    private String country;
    private String phone;
    @Transient
    private int age;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String email, LocalDate dateOfBirth, String address, String country, String phone) {
        this.firstName = firstName;
        this.lastName =lastName;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.country = country;
        this.phone = phone;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return dateOfBirth == null ? null : Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        return "Contact{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
