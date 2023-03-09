package com.andrefilho.contactList.converters;

import com.andrefilho.contactList.persistence.model.Contact;
import com.andrefilho.contactList.command.ContactDto;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactDto extends AbstractConverter<Contact, ContactDto>{

    public ContactDto convert(Contact contact){
        ContactDto contactDto = new ContactDto();

        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setEmail(contact.getEmail());
        contactDto.setDateOfBirth(contact.getDateOfBirth());
        contactDto.setAddress(contact.getAddress());
        contactDto.setCountry(contact.getCountry());
        contactDto.setPhone(contact.getPhone());
        contactDto.setAge(contact.getAge());

        return contactDto;
    }
}
