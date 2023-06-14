package com.clinic.pageController;

import com.clinic.controller.SurgeryController;
import com.clinic.controller.UserController;
import com.clinic.controller.VisitController;
import com.clinic.controller.WorkScheduleController;
import com.clinic.dbTables.User;
import com.clinic.dbTables.Visit;
import com.clinic.dbTables.VisitStatus;
import com.clinic.dbTables.WorkSchedule;
import com.clinic.dto.VisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class DoctorPageController {

  @Autowired
  private UserController userController;
  @Autowired
  private SurgeryController surgeryController;
  @Autowired
  private WorkScheduleController workScheduleController;
  @Autowired
  private VisitController visitController;

  @GetMapping("/doctor/workTime")
  public String getWorkTime(Model model){
    User user = getUser();
    List<WorkSchedule> workSchedules = workScheduleController.getSchedulesForUser(user.getId());
    model.addAttribute("workSchedules", workSchedules);
    return "doctorManageWorktime";
  }

  @GetMapping("/doctor/work/new")
  public String addWorkSchedule(Model model){
    model.addAttribute("schedule", new WorkSchedule());
    return "addWorksShedulePage";
  }

  @PostMapping("/doctor/work/new")
  public String newWorkSchedule(@ModelAttribute("schedule")WorkSchedule workSchedule, Model model){
    User user = getUser();
    workSchedule.setDoctorId(user.getId());
    workScheduleController.addWorkSchedule(workSchedule);
    return "goBackToMain";
  }

  @GetMapping("doctor/change/schedule/{id}")
  public String changeSchedule(@PathVariable("id") String id, Model model){
    WorkSchedule workSchedule = workScheduleController.getWorkScheduleBuId(Integer.parseInt(id));
    model.addAttribute("schedule", workSchedule);
    return "changeSchedulePage";
  }

  @GetMapping("/doctor/delete/schedule/{id}")
  public String deleteSchedule(@PathVariable("id") String id){
    workScheduleController.deleteWorkSchedule(Integer.parseInt(id));
    return "goBackToMain";
  }

  @GetMapping("/doctor/visits")
  public String getDoctorVisit(Model model){
    List<Visit> rawVisits = visitController.getDoctorVisits(getUser().getId());
    List<VisitDTO> visits = new ArrayList<>();
    for(Visit visit : rawVisits){
      if(visit.getStatus() != VisitStatus.CANCELED){
        visits.add(getVisitDTO(visit, userController, surgeryController));
      }
    }
    model.addAttribute("visits", visits);
    return "doctorVisitPage";
  }

  @GetMapping("/doctor/visit/cancel/{id}")
  public String cancelVisit(@PathVariable String id, Model model){

    visitController.deleteVisitById(Integer.parseInt(id));

    List<Visit> rawVisits = visitController.getDoctorVisits(getUser().getId());
    List<VisitDTO> visits = new ArrayList<>();
    for(Visit visit : rawVisits){
      if(visit.getStatus() != VisitStatus.CANCELED){
        visits.add(getVisitDTO(visit, userController, surgeryController));
      }
    }
    model.addAttribute("visits", visits);
    return "doctorVisitPage";
  }

  @GetMapping("/doctor/fill/visit/{id}")
  public String fillVisit(@PathVariable("id") String id, Model model){
    Visit visit = visitController.getVisitById(Integer.parseInt(id));
    model.addAttribute("visit", visit);
    return "doctorFillVisitPage";
  }

  @PostMapping("/doctor/fill/visit")
  public String fillVisit(@ModelAttribute("visit") Visit visit, Model model){
    int id = visit.getVisitId();
    Visit newVisit = visitController.getVisitById(id);
    newVisit.setStatus(visit.getStatus());
    newVisit.setProgressAssessment(visit.getProgressAssessment());
    newVisit.setNotes(visit.getNotes());
    visitController.updateVisit(newVisit);

    List<Visit> rawVisits = visitController.getDoctorVisits(getUser().getId());
    List<VisitDTO> visits = new ArrayList<>();
    for(Visit fvisit : rawVisits){
      if(fvisit.getStatus() != VisitStatus.CANCELED){
        visits.add(getVisitDTO(fvisit, userController, surgeryController));
      }
    }
    model.addAttribute("visits", visits);
    return "doctorVisitPage";
  }

  public User getUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    return userController.getUserByUsername(currentPrincipalName);
  }

  static VisitDTO getVisitDTO(Visit visit, UserController userController, SurgeryController surgeryController) {
    VisitDTO visitDTO = new VisitDTO();
    visitDTO.setId(visit.getVisitId());
    visitDTO.setClient(userController.getUserById(visit.getClientId()).getName() + " " + userController.getUserById(visit.getClientId()).getSurname());
    visitDTO.setDoctor(userController.getUserById(visit.getDoctorId()).getName());
    visitDTO.setDate(visit.getDate());
    visitDTO.setSurgery(surgeryController.getSurgeryById(visit.getSurgeryId()).getName());
    visitDTO.setStatus(visit.getStatus().toString());
    return visitDTO;
  }
}
