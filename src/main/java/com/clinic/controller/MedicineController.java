package com.clinic.controller;

import com.clinic.dbRepository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicineController {

    @Autowired
    MedicineRepository medicineRepository;
}
