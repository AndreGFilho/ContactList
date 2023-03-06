package com.andrefilho.contactList;

import com.andrefilho.contactList.contact.Contact;
import com.andrefilho.contactList.persistence.ContactRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class ContactConfig {

    @Bean
    CommandLineRunner commandLineRunner(ContactRepository repository){
        return args -> {
            Contact andre = new Contact(
                    "Andre",
                    "email@gmail.com",
                    LocalDate.of(2010, Month.APRIL,12),
                    "address",
                    "country",
                    null
            );
            Contact bianca = new Contact(
                    "Bianca",
                    "emailBianca@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,22),
                    "address2",
                    "country2",
                    null
            );
            repository.saveAll(
                    List.of(andre,bianca)
            );
        };
    }
}
