//package com.andrefilho.contactList;
//
//import com.andrefilho.contactList.persistence.model.ContactPerson;
//import com.andrefilho.contactList.persistence.jpa.PersonRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.Month;
//import java.util.List;
//
//@Configuration
//public class ContactConfig {
//
//    @Bean
//    CommandLineRunner commandLineRunner(PersonRepository repository){
//        return args -> {
//            ContactPerson andre = new ContactPerson(
//                    "Andre",
//                    "Filho",
//                    "email@gmail.com",
//                    LocalDate.of(2010, Month.APRIL,12),
//                    "address",
//                    "country",
//                    null
//            );
//            ContactPerson bianca = new ContactPerson(
//                    "Bianca",
//                    "Gassman",
//                    "emailBianca@gmail.com",
//                    LocalDate.of(2000, Month.JANUARY,22),
//                    "address2",
//                    "country2",
//                    null
//            );
//            repository.saveAll(
//                    List.of(andre,bianca)
//            );
//        };
//    }
//}
