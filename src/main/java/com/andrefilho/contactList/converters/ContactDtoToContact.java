package com.andrefilho.contactList.converters;

import com.andrefilho.contactList.contact.Contact;
import com.andrefilho.contactList.contact.ContactDto;
import com.andrefilho.contactList.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoToContact implements Converter<ContactDto, Contact> {

    private ContactService contactService;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Override
    public Contact convert(ContactDto contactDto) {
        Contact contact = (contactDto.getId() != null ? contactService.getContact(contactDto.getId()) : new Contact());

        contact.setName(contactDto.getName());
        contact.setEmail(contactDto.getEmail());
        contact.setDateOfBirth(contactDto.getDateOfBirth());
        contact.setAddress(contactDto.getAddress());
        contact.setCountry(contactDto.getCountry());
        contact.setPhone(contactDto.getPhone());

        return contact;

    }
}
