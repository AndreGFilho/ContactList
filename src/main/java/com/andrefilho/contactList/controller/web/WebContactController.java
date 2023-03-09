package com.andrefilho.contactList.controller.web;

import com.andrefilho.contactList.command.ContactPersonDto;
import com.andrefilho.contactList.persistence.model.ContactPerson;
import com.andrefilho.contactList.converters.ContactPersonDtoToContactPerson;
import com.andrefilho.contactList.converters.ContactPersonToContactPersonDto;
import com.andrefilho.contactList.exceptions.ContactPersonNotFoundException;
import com.andrefilho.contactList.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping(path = "/contacts")
public class WebContactController {

    private PersonService personService;
    private ContactPersonToContactPersonDto contactPersonToContactPersonDto;
    private ContactPersonDtoToContactPerson contactPersonDtoToContactPerson;

    @Autowired
    public void setContactService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setContactToContactDto(ContactPersonToContactPersonDto contactPersonToContactPersonDto) {
        this.contactPersonToContactPersonDto = contactPersonToContactPersonDto;
    }

    @Autowired
    public void setContactDtoToContact(ContactPersonDtoToContactPerson contactPersonDtoToContactPerson) {
        this.contactPersonDtoToContactPerson = contactPersonDtoToContactPerson;
    }

    @GetMapping(path = {"/list", "/", ""})
    public String listContacts(Model model){
        model.addAttribute("contacts", contactPersonToContactPersonDto.convert(personService.list()));
        return "contact/list";
    }
    @GetMapping (path = "/{id}")
    public String showContact(@PathVariable long id, Model model) throws Exception {

        ContactPerson contactPerson = personService.getContact(id);

        // command objects for customer show view
        model.addAttribute("contact", contactPersonToContactPersonDto.convert(contactPerson));
        return "contact/show";


//        model.addAttribute("accounts", accountToAccountDto.convert(customer.getAccounts()));
//        model.addAttribute("accountTypes", AccountType.list());
//        model.addAttribute("customerBalance", customerService.getBalance(id));

/*
        // command objects for modals
        AccountDto accountDto = new AccountDto();
        AccountTransactionDto accountTransactionDto = new AccountTransactionDto();
        accountTransactionDto.setId(id);

        model.addAttribute("account", accountDto);
        model.addAttribute("accountTransaction", accountTransactionDto);

        model.addAttribute("transfer", new TransferDto());*/
    }
    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addContact(Model model) {
        model.addAttribute("contact", new ContactPersonDto());
        return "contact/add-update";
    }
    @PostMapping(path = {"/", ""}, params = "action=save")
    public String saveCustomer(@Valid @ModelAttribute("contact") ContactPersonDto contactPersonDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            return "contact/add-update";
        }

        ContactPerson savedContactPerson = personService.save(contactPersonDtoToContactPerson.convert(contactPersonDto));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedContactPerson.getFirstName());

        return "redirect:/contacts/" + savedContactPerson.getId();
    }

    @PostMapping(path = {"/", ""}, params = "action=cancel")
    public String cancelSaveContact() {
        return "redirect:/contacts";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", contactPersonToContactPersonDto.convert(personService.getContact(id)));
        return "contact/add-update";
    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws ContactPersonNotFoundException {
        ContactPerson contactPerson = personService.getContact(id);

        personService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + contactPerson.getFirstName());
        return "redirect:/contacts";
    }

}
