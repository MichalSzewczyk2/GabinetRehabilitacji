package com.clinic.dbTables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
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
