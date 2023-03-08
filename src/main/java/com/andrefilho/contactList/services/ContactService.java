package com.andrefilho.contactList.services;

import com.andrefilho.contactList.contact.Contact;
import com.andrefilho.contactList.errors.ErrorMessage;
import com.andrefilho.contactList.exceptions.ContactNotFoundException;
import com.andrefilho.contactList.persistence.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> list(){
        return contactRepository.findAll();}

    public Contact getContact(long contactId){
        return contactRepository.getReferenceById(contactId);
    }
    public Contact addNewContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public void updateContact(Contact contact) {

        contactRepository.save(contact);
    }

    public void deleteContact(long contactID) throws ContactNotFoundException {

        if (!contactRepository.existsById(contactID)) {
            throw new ContactNotFoundException(ErrorMessage.CONTACT_NOT_FOUND);
        }
        contactRepository.deleteById(contactID);
    }

    @Transactional
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }


    @Transactional
    public void delete(long id) throws ContactNotFoundException {
        Contact contact = contactRepository.findById(id)
                .orElseThrow();

        contactRepository.deleteById(id);
    }
}
