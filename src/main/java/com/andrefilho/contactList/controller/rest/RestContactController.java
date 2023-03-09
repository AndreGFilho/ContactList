package com.andrefilho.contactList.controller.rest;

import com.andrefilho.contactList.contact.Contact;
import com.andrefilho.contactList.contact.ContactDto;
import com.andrefilho.contactList.services.ContactService;
import com.andrefilho.contactList.converters.ContactDtoToContact;
import com.andrefilho.contactList.converters.ContactToContactDto;
import com.andrefilho.contactList.exceptions.ContactNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(path = "/api")
public class RestContactController {


    private final ContactService contactService;
    private ContactToContactDto contactToContactDto;
    private ContactDtoToContact contactDtoToContact;

    @Autowired
    public void setContactToContactDto(ContactToContactDto contactToContactDto) {
        this.contactToContactDto = contactToContactDto;
    }

    @Autowired
    public void setContactDtoToContact(ContactDtoToContact contactDtoToContact) {
        this.contactDtoToContact = contactDtoToContact;
    }

    @Autowired
    public RestContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(path = {"/contacts", "","/"})
    public ResponseEntity<List<ContactDto>> listContacts() {
        List<ContactDto> contactDtos = contactService.list().stream()
                .map(contact -> contactToContactDto.convert(contact))
                .collect(Collectors.toList());

        return new ResponseEntity<>(contactDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactDto> getContact(
            @PathVariable("id") Long contactId
    ) {
        Contact contact = contactService.getContact(contactId);

        if (contact == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contactToContactDto.convert(contact), HttpStatus.OK);

    }


    @PostMapping(path = {"/","","/addContact"})
    public ResponseEntity<?> registerContact(@Valid @RequestBody ContactDto contactDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors() || contactDto.getId() != null) {
            return new ResponseEntity<>((HttpStatus.BAD_REQUEST));
        }

        Contact savedContact = contactService.addNewContact(contactDtoToContact.convert(contactDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/" + savedContact.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ContactDto> updateContact(
            @PathVariable("id") Long contactId,
            @Valid @RequestBody ContactDto contactDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (contactDto.getId() != null && !contactDto.getId().equals(contactId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (contactService.getContact(contactId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        contactDto.setId(contactId);

        contactService.save(contactDtoToContact.convert(contactDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ContactDto> deleteContact(
            @PathVariable("id") Long contactId) {

        try {
            contactService.delete(contactId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        } catch (ContactNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
