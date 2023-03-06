package com.andrefilho.contactList.controller.web;

import com.andrefilho.contactList.converters.ContactDtoToContact;
import com.andrefilho.contactList.converters.ContactToContactDto;
import com.andrefilho.contactList.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/contacts")
public class WebContactController {

    private ContactService contactService;
    private ContactToContactDto contactToContactDto;
    private ContactDtoToContact contactDtoToContact;

    @Autowired
    public void setContactService(ContactService contactService) {
        this.contactService = contactService;
    }

    @Autowired
    public void setContactToContactDto(ContactToContactDto contactToContactDto) {
        this.contactToContactDto = contactToContactDto;
    }

    @Autowired
    public void setContactDtoToContact(ContactDtoToContact contactDtoToContact) {
        this.contactDtoToContact = contactDtoToContact;
    }

    @GetMapping(path = {"/list", "/", ""})
    public String listContacts(Model model){
        model.addAttribute("contacts", contactToContactDto.convert(contactService.list()));
        return "contact/list";
    }

}
