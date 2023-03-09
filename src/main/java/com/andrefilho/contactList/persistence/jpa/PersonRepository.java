package com.andrefilho.contactList.persistence.jpa;

import com.andrefilho.contactList.persistence.model.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<ContactPerson, Long> {
}
