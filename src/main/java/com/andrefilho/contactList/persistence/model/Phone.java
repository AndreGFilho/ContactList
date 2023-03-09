package com.andrefilho.contactList.persistence.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Transient;

@Entity
public class Phone extends ContactInformation {

    private String number;
    private boolean whatsapp;
    @Transient
    @Override
    public InformationType getInformationType() {
        return InformationType.PHONE;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(boolean whatsapp) {
        this.whatsapp = whatsapp;
    }
}
