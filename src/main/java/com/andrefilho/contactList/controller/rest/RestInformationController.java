package com.andrefilho.contactList.controller.rest;


import com.andrefilho.contactList.services.InformationService;
import com.andrefilho.contactList.services.PersonService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/contacts")
public class RestInformationController {

    private PersonService personService;
    private InformationService informationService;


}
