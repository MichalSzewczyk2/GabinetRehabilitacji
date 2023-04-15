package com.clinic.controller;

import com.clinic.dbRepository.MedicinePatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicinePatientController {

    @Autowired
    MedicinePatientRepository medicinePatientRepository;
}
