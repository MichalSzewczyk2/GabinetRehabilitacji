package com.clinic.dbRepository;

import lombok.Data;

@Data
public class Clinic {
    private int id;
    private String name;
    private String address;
    private int phoneNumber;
    private String email;

}
