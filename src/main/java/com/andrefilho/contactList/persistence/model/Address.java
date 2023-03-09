package com.andrefilho.contactList.persistence.model;

public class Address extends ContactInformation {
    private String address;
    private String country;
    private String city;
    private String postcode;
    @Override
    public InformationType getInformationType() {
        return InformationType.ADDRESS;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
