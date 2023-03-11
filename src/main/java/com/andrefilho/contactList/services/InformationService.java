package com.andrefilho.contactList.services;

import com.andrefilho.contactList.persistence.jpa.InformationRepository;
import com.andrefilho.contactList.persistence.jpa.PersonRepository;
import com.andrefilho.contactList.persistence.model.ContactInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformationService {
    private InformationRepository informationRepository;
    private PersonRepository personRepository;

    @Autowired
    public void setInformationRepository(InformationRepository informationRepository) {
        this.informationRepository = informationRepository;
    }

    @Autowired
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public ContactInformation get(Long id) {return informationRepository.getReferenceById(id);}

}
