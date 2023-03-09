package com.andrefilho.contactList.persistence.model;

import java.util.Arrays;
import java.util.List;

public enum InformationType {

    PHONE,
    ADDRESS,
    EMAIL;

public static List<InformationType> list(){ return Arrays.asList(InformationType.values());}


}
