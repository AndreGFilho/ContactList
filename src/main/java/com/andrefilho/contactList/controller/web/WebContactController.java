package com.andrefilho.contactList.controller.web;

import com.andrefilho.contactList.contact.Contact;
import com.andrefilho.contactList.contact.ContactDto;
import com.andrefilho.contactList.converters.ContactDtoToContact;
import com.andrefilho.contactList.converters.ContactToContactDto;
import com.andrefilho.contactList.exceptions.ContactNotFoundException;
import com.andrefilho.contactList.services.ContactService;
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
    @GetMapping (path = "/{id}")
    public String showContact(@PathVariable long id, Model model) throws Exception {

        Contact contact = contactService.getContact(id);

        // command objects for customer show view
        model.addAttribute("contact", contactToContactDto.convert(contact));
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
        model.addAttribute("contact", new ContactDto());
        return "contact/add-update";
    }
    @PostMapping(path = {"/", ""}, params = "action=save")
    public String saveCustomer(@Valid @ModelAttribute("contact") ContactDto contactDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            return "contact/add-update";
        }

        Contact savedContact = contactService.save(contactDtoToContact.convert(contactDto));

        redirectAttributes.addFlashAttribute("lastAction", "Saved " + savedContact.getName());

        return "redirect:/contacts/" + savedContact.getId();
    }

    @PostMapping(path = {"/", ""}, params = "action=cancel")
    public String cancelSaveContact() {
        return "redirect:/contacts";
    }
    @RequestMapping(method = RequestMethod.GET, path = "/{id}/edit")
    public String editCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("contact", contactToContactDto.convert(contactService.getContact(id)));
        return "contact/add-update";
    }
    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id, RedirectAttributes redirectAttributes) throws ContactNotFoundException {
        Contact contact = contactService.getContact(id);

        contactService.delete(id);
        redirectAttributes.addFlashAttribute("lastAction", "Deleted " + contact.getName());
        return "redirect:/contacts";
    }

}
