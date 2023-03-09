package com.andrefilho.contactList.persistence.model;

public class Email extends ContactInformation {
    private String email;
    @Override
    public InformationType getInformationType() {
        return InformationType.EMAIL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
