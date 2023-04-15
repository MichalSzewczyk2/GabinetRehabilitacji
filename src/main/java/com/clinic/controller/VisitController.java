package com.clinic.controller;

import com.clinic.dbRepository.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitController {

  @Autowired
  VisitRepository visitRepository;
}
