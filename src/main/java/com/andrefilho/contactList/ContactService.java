package com.andrefilho.contactList;

import com.andrefilho.contactList.Contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getContacts(){
        return contactRepository.findAll();
    }

    public void addNewContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void updateContact(Long contactId, Contact contact) {

        Contact contact2 = contactRepository.getReferenceById(contactId);

        contact2.setName(contact.getName());
        contact2.setEmail(contact.getEmail());
        contact2.setAddress(contact.getAddress());
        contact2.setCountry(contact.getCountry());
        contact2.setDateOfBirth(contact.getDateOfBirth());
    }

    public void deleteContact(long contactID)
}
