package com.clinic.controller;

import com.clinic.dbRepository.SurgeryRepository;
import com.clinic.dbTables.Surgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SurgeryController {

  @Autowired
  SurgeryRepository surgeryRepository;

  public Surgery getSurgeryById(int id){
    return surgeryRepository.getById(id);
  }
}
