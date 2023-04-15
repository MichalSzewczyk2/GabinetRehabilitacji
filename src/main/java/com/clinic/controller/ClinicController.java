package com.clinic.controller;

import com.clinic.dbRepository.ClinicRepository;
import com.clinic.dbTables.Clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClinicController {

    @Autowired
    ClinicRepository clinicRepository;

    @GetMapping("/clinics")
    public List<Clinic> getAll(){
        return clinicRepository.getAll();
    }
}
