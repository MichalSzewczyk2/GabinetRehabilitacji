package com.clinic.dbTables;

import lombok.Data;

@Data
public class Surgery {
    private int surgeryId;
    private String name;
    private UserType authorizationNeeded;
    private int regularPrice;
    private int prescriptionPrice;
    private String description;
    private int duration;

}
