package com.clinic.controller;

import com.clinic.dbRepository.SurgeryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurgeryController {

    @Autowired
    SurgeryRepository surgeryRepository;

}
