package com.andrefilho.contactList.converters;

import com.andrefilho.contactList.command.ContactPersonDto;
import com.andrefilho.contactList.persistence.model.ContactPerson;
import org.springframework.stereotype.Component;

@Component
public class ContactPersonToContactPersonDto extends AbstractConverter<ContactPerson, ContactPersonDto>{

    public ContactPersonDto convert(ContactPerson contactPerson){
        ContactPersonDto contactPersonDto = new ContactPersonDto();

        contactPersonDto.setId(contactPerson.getId());
        contactPersonDto.setFirstName(contactPerson.getFirstName());
        contactPersonDto.setLastName(contactPersonDto.getLastName());
        contactPersonDto.setEmail(contactPerson.getEmail());
        contactPersonDto.setDateOfBirth(contactPerson.getDateOfBirth());
        contactPersonDto.setAddress(contactPerson.getAddress());
        contactPersonDto.setCountry(contactPerson.getCountry());
        contactPersonDto.setPhone(contactPerson.getPhone());
        contactPersonDto.setAge(contactPerson.getAge());

        return contactPersonDto;
    }
}
