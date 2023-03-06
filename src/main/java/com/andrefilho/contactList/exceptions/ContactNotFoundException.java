package com.andrefilho.contactList.exceptions;

import com.andrefilho.contactList.errors.ErrorMessage;

public class ContactNotFoundException extends CustomExceptions{
    public ContactNotFoundException(String message) {
        super(ErrorMessage.CONTACT_NOT_FOUND);
    }
}
