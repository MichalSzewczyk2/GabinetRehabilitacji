package com.clinic.controller;

import com.clinic.dbRepository.WorkScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkScheduleController {

  @Autowired
  WorkScheduleRepository workScheduleRepository;
}
