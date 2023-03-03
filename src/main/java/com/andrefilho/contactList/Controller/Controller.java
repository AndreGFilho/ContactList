package com.andrefilho.contactList.Controller;

import com.andrefilho.contactList.Contact.Contact;
import com.andrefilho.contactList.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class Controller {

    private final ContactService contactService;

    @Autowired
    public Controller(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(path = "/contacts")
    public List<Contact> getContacts() {
        return contactService.getContacts();
    }

    @PostMapping(path = "/addContact")
    public void registerContact(@RequestBody Contact contact) {
        contactService.addNewContact(contact);
    }

    @PutMapping(path = "/{id}")
    public void updateContact(
            @PathVariable("id") Long contactId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        contactService.updateContact(contactId, name, email);
    }


}
