package com.clinic.controller;

import com.clinic.dbRepository.VisitRepository;
import com.clinic.dbTables.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VisitController {

  @Autowired
  VisitRepository visitRepository;
  @Autowired
  UserController userController;

  public List<Visit> getAllUserVisits(int userId) {
    List<Visit> visits = visitRepository.getAll();
    List<Visit> result = new ArrayList<Visit>();
    for (Visit visit : visits) {
      if(visit.getClientId() == userId){
        result.add(visit);
      }
    }
    return result;
  }

}
