package com.andrefilho.contactList.exceptions;

import com.andrefilho.contactList.errors.ErrorMessage;

public class ContactPersonNotFoundException extends CustomExceptions{
    public ContactPersonNotFoundException(String message) {
        super(ErrorMessage.CONTACT_NOT_FOUND);
    }
}
