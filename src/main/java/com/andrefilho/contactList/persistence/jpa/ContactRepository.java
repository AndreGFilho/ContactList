package com.andrefilho.contactList.persistence.jpa;

import com.andrefilho.contactList.persistence.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
