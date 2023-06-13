package com.clinic.controller;

import com.clinic.dbRepository.WorkScheduleRepository;
import com.clinic.dbTables.WorkSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WorkScheduleController {

  @Autowired
  WorkScheduleRepository workScheduleRepository;

  public List<WorkSchedule> getSchedulesForUser(int userId) {
    List<WorkSchedule> schedules = workScheduleRepository.getAll();
    List<WorkSchedule> userScheduleList = new ArrayList<>();
    for(WorkSchedule schedule : schedules){
      if(schedule.getDoctorId() == userId)userScheduleList.add(schedule);
    }
    return userScheduleList;
  }

  public WorkSchedule workingThatDay(int userId, LocalDate date){
    List<WorkSchedule> schedules = workScheduleRepository.getAll();
    for(WorkSchedule schedule : schedules){
      if(schedule.getDoctorId() == userId && schedule.getDate().equals(date)){
        return schedule;
      }
    }
    return null;
  }
}
