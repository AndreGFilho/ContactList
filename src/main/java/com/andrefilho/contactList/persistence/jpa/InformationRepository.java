package com.andrefilho.contactList.persistence.jpa;

import com.andrefilho.contactList.persistence.model.ContactInformation;
import com.andrefilho.contactList.persistence.model.InformationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InformationRepository extends JpaRepository<ContactInformation, Long> {
}
