package com.andrefilho.contactList.controller.rest;

import com.andrefilho.contactList.command.ContactPersonDto;
import com.andrefilho.contactList.persistence.model.ContactPerson;
import com.andrefilho.contactList.services.PersonService;
import com.andrefilho.contactList.converters.ContactPersonDtoToContactPerson;
import com.andrefilho.contactList.converters.ContactPersonToContactPersonDto;
import com.andrefilho.contactList.exceptions.ContactPersonNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class RestContactController {


    private final PersonService personService;
    private ContactPersonToContactPersonDto contactPersonToContactPersonDto;
    private ContactPersonDtoToContactPerson contactPersonDtoToContactPerson;

    @Autowired
    public void setContactToContactDto(ContactPersonToContactPersonDto contactPersonToContactPersonDto) {
        this.contactPersonToContactPersonDto = contactPersonToContactPersonDto;
    }

    @Autowired
    public void setContactDtoToContact(ContactPersonDtoToContactPerson contactPersonDtoToContactPerson) {
        this.contactPersonDtoToContactPerson = contactPersonDtoToContactPerson;
    }

    @Autowired
    public RestContactController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping(path = {"/contacts", "","/"})
    public ResponseEntity<List<ContactPersonDto>> listContacts() {
        List<ContactPersonDto> contactPersonDtos = personService.list().stream()
                .map(contact -> contactPersonToContactPersonDto.convert(contact))
                .collect(Collectors.toList());

        return new ResponseEntity<>(contactPersonDtos, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ContactPersonDto> getContact(
            @PathVariable("id") Long contactId
    ) {
        ContactPerson contactPerson = personService.getContact(contactId);

        if (contactPerson == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(contactPersonToContactPersonDto.convert(contactPerson), HttpStatus.OK);

    }


    @PostMapping(path = {"/","","/addContact"})
    public ResponseEntity<?> registerContact(@Valid @RequestBody ContactPersonDto contactPersonDto, BindingResult bindingResult, UriComponentsBuilder uriComponentsBuilder) {
        if (bindingResult.hasErrors() || contactPersonDto.getId() != null) {
            return new ResponseEntity<>((HttpStatus.BAD_REQUEST));
        }

        ContactPerson savedContactPerson = personService.addNewContact(contactPersonDtoToContactPerson.convert(contactPersonDto));

        UriComponents uriComponents = uriComponentsBuilder.path("/api/" + savedContactPerson.getId()).build();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);

    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ContactPersonDto> updateContact(
            @PathVariable("id") Long contactId,
            @Valid @RequestBody ContactPersonDto contactPersonDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (contactPersonDto.getId() != null && !contactPersonDto.getId().equals(contactId)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (personService.getContact(contactId) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        contactPersonDto.setId(contactId);

        personService.save(contactPersonDtoToContactPerson.convert(contactPersonDto));

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<ContactPersonDto> deleteContact(
            @PathVariable("id") Long contactId) {

        try {
            personService.delete(contactId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);

        } catch (ContactPersonNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }


}
