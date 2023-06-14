package com.clinic.controller;

import com.clinic.dbRepository.VisitRepository;
import com.clinic.dbTables.Visit;
import com.clinic.dbTables.VisitStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalTime;
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
    List<Visit> result = new ArrayList<>();
    for (Visit visit : visits) {
      if(visit.getClientId() == userId){
        result.add(visit);
      }
    }
    return result;
  }

  public List<Visit> getVisitByDayAndDoctor(LocalDate date, int doctorId){
    List<Visit> result = new ArrayList<>();
    List<Visit> visits = visitRepository.getAll();
    for(Visit visit: visits){
      if(visit.getDate().equals(date) && visit.getDoctorId() == doctorId){
        result.add(visit);
      }
    }
    return result;
  }

  public Visit getVisitById(int id){
    return visitRepository.getById(id);
  }

  public void addVisit(LocalDate date, LocalTime start, int clientId, int doctorId, int surgeryId){
    Visit visit = new Visit();
    visit.setStatus(VisitStatus.PENDING);
    visit.setDate(date);
    visit.setTime(start);
    visit.setClientId(clientId);
    visit.setDoctorId(doctorId);
    visit.setSurgeryId(surgeryId);
    visitRepository.add(visit);
  }

  public void deleteVisitById(int id){
    visitRepository.delete(id);
  }

  public List<Visit> getDoctorVisits(int id){
    List<Visit> result = new ArrayList<>();
    List<Visit> visits = visitRepository.getAll();
    for(Visit visit : visits){
      if(visit.getDoctorId() == id){
        result.add(visit);
      }
    }
    return result;
  }

  public void updateVisit(Visit visit){
    visitRepository.update(visit);
  }
}
