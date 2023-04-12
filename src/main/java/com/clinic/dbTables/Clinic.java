package com.clinic.dbTables;

import lombok.Data;

@Data
public class Clinic {
    private int clinicId;
    private String name;
    private String address;
    private int phoneNumber;
    private String email;

}
