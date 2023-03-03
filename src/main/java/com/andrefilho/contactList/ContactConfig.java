package com.andrefilho.contactList;

import com.andrefilho.contactList.Contact.Contact;
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
                    "email",
                    LocalDate.of(2010, Month.APRIL,12),
                    "address",
                    "country"
            );
            Contact bianca = new Contact(
                    "Bianca",
                    "emailBianca",
                    LocalDate.of(2000, Month.JANUARY,22),
                    "address2",
                    "country2"
            );
            repository.saveAll(
                    List.of(andre,bianca)
            );
        };
    }
}
