package com.andrefilho.contactList.persistence.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Entity
@Table(name = "contact_person")
public class ContactPerson extends AbstractModel {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    @Transient
    private int age;
    @OneToMany(
            cascade = {CascadeType.ALL},
            orphanRemoval = true,
            mappedBy = "contact_person",
            fetch = FetchType.EAGER
    )
    private List<ContactInformation> contactInformationList;

    public ContactPerson() {
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return dateOfBirth == null ? null : Period.between(this.dateOfBirth, LocalDate.now()).getYears();
    }

    public List<ContactInformation> getContactInformationList() {
        return contactInformationList;
    }

    public void addContactInformation(ContactInformation contactInformation) {
        contactInformationList.add(contactInformation);
        contactInformation.setContactPerson(this);
    }

    public void removeContactInformation (ContactInformation contactInformation){
        contactInformationList.remove(contactInformation);
        contactInformation.setContactPerson(null);
    }

    @Override
    public String toString() {
        return "ContactPerson{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", age=" + age +
                ", contactInformationList=" + contactInformationList +
                '}' + super.toString();
    }
}
