package com.andrefilho.contactList.converters;

import com.andrefilho.contactList.contact.Contact;
import com.andrefilho.contactList.contact.ContactDto;
import org.springframework.stereotype.Component;

@Component
public class ContactToContactDto extends AbstractConverter<Contact, ContactDto>{

    public ContactDto convert(Contact contact){
        ContactDto contactDto = new ContactDto();

        contactDto.setId(contact.getId());
        contactDto.setName(contact.getName());
        contactDto.setEmail(contact.getEmail());
        contactDto.setDateOfBirth(contact.getDateOfBirth());
        contactDto.setAddress(contact.getAddress());
        contactDto.setCountry(contact.getCountry());
        contactDto.setPhone(contact.getPhone());
        contactDto.setAge(contact.getAge());

        return contactDto;
    }
}
