package com.andrefilho.contactList.services;

import com.andrefilho.contactList.persistence.model.ContactPerson;
import com.andrefilho.contactList.errors.ErrorMessage;
import com.andrefilho.contactList.exceptions.ContactPersonNotFoundException;
import com.andrefilho.contactList.persistence.jpa.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<ContactPerson> list(){
        return personRepository.findAll();}

    public ContactPerson getContact(long contactId){
        return personRepository.getReferenceById(contactId);
    }
    public ContactPerson addNewContact(ContactPerson contactPerson) {
        return personRepository.save(contactPerson);
    }

    @Transactional
    public void delete(long contactID) throws ContactPersonNotFoundException {

        if (!personRepository.existsById(contactID)) {
            throw new ContactPersonNotFoundException(ErrorMessage.CONTACT_NOT_FOUND);
        }
        personRepository.deleteById(contactID);
    }

    @Transactional
    public ContactPerson save(ContactPerson contactPerson) {
        return personRepository.save(contactPerson);
    }

}
