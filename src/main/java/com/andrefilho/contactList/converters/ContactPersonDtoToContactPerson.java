package com.andrefilho.contactList.converters;

import com.andrefilho.contactList.command.ContactPersonDto;
import com.andrefilho.contactList.persistence.model.ContactPerson;
import com.andrefilho.contactList.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;



@Component
public class ContactPersonDtoToContactPerson implements Converter<ContactPersonDto, ContactPerson> {

    private PersonService personService;

    @Autowired
    public void setContactService(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public ContactPerson convert(ContactPersonDto contactPersonDto) {
        ContactPerson contactPerson = (contactPersonDto.getId() != null ? personService.getContact(contactPersonDto.getId()) : new ContactPerson());

        contactPerson.setFirstName(contactPersonDto.getFirstName());
        contactPerson.setLastName(contactPersonDto.getLastName());
        contactPerson.setEmail(contactPersonDto.getEmail());
        contactPerson.setDateOfBirth(contactPersonDto.getDateOfBirth());
        contactPerson.setAddress(contactPersonDto.getAddress());
        contactPerson.setCountry(contactPersonDto.getCountry());
        contactPerson.setPhone(contactPersonDto.getPhone());

        return contactPerson;

    }
}
