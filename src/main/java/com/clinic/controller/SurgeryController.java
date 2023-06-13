package com.clinic.controller;

import com.clinic.dbRepository.SurgeryRepository;
import com.clinic.dbTables.Surgery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SurgeryController {

  @Autowired
  SurgeryRepository surgeryRepository;

  public Surgery getSurgeryById(int id){
    return surgeryRepository.getById(id);
  }

  public List<Surgery> getAllSurgeries(){
    return surgeryRepository.getAll();
  }

  public Surgery getSurgeryByName(String name){
    List<Surgery> all = surgeryRepository.getAll();
    for(Surgery surgery : all){
      if(surgery.getName().equals(name)){
        return surgery;
      }
    }
    return null;
  }
}
