package com.clinic.dbRepository;

import lombok.Data;

import java.time.LocalDate;
@Data
public class User {

    private int id;
    private String name;
    private String surname;
    private String address;
    private LocalDate birthDate;
    private int phoneNumber;
    private String email;
    private UserType userType;
    private String username;
    private String password;

}
