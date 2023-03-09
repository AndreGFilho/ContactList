package com.andrefilho.contactList.persistence.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "information_type")
public abstract class ContactInformation extends AbstractModel{

    @ManyToOne
    private ContactPerson contactPerson;

    private ContactInformationSource contactInformationSource;

    public ContactPerson getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(ContactPerson contactPerson) {
        this.contactPerson = contactPerson;
    }

    public abstract InformationType getInformationType();
    public ContactInformationSource getContactInformationSource(){
        return contactInformationSource;
    }
    public void setContactInformationSource(ContactInformationSource contactInformationSource) {
        this.contactInformationSource = contactInformationSource;
    }

    @Override
    public String toString() {
        return "ContactInformation{" +
                "contactPerson=" + contactPerson +
                ", contactInformationSource=" + contactInformationSource +
                ", contactId=" +(contactPerson != null ? contactPerson.getId() : null) +
                '}' + super.toString();
    }
}
