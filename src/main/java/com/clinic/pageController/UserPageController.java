package com.clinic.pageController;

import com.clinic.controller.SurgeryController;
import com.clinic.controller.UserController;
import com.clinic.controller.VisitController;
import com.clinic.controller.WorkScheduleController;
import com.clinic.dbTables.Surgery;
import com.clinic.dbTables.User;
import com.clinic.dbTables.Visit;
import com.clinic.dbTables.WorkSchedule;
import com.clinic.dto.FullVisitDTO;
import com.clinic.dto.StringDTO;
import com.clinic.dto.UserVisitDTO;
import com.clinic.dto.VisitDTO;
import com.clinic.helpers.Intervals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserPageController {

  @Autowired
  private UserController userController;
  @Autowired
  private SurgeryController surgeryController;
  @Autowired
  private WorkScheduleController workScheduleController;
  @Autowired
  private VisitController visitController;

  @GetMapping("/user/profile")
  public String getUserProfile(Model model){
    User user = getUser();
    model.addAttribute("user", user);
    return "userProfilePage";
  }

  @GetMapping("/user/visit")
  public String getUserVisit(Model model){
    User user = getUser();
    List<Surgery> surgeries = surgeryController.getAllSurgeries();
    model.addAttribute("user", user);
    model.addAttribute("visit", new UserVisitDTO());
    model.addAttribute("surgeries", surgeries);
    return "userVisitPage";
  }

  @PostMapping("/user/visit")
  public String getUserVisit(@ModelAttribute("visit")UserVisitDTO visitDTO, Model model){
    Surgery surgery = surgeryController.getSurgeryByName(visitDTO.getVisitName());
    List<User> doctors = userController.getDoctorsWithPermissionsWorkingOnDate(surgery.getAuthorizationNeeded().name(), visitDTO.getDate());
    model.addAttribute("visit", visitDTO);
    model.addAttribute("doctors", doctors);
    model.addAttribute("doctor", null);
    model.addAttribute("availableHours", null);
    return "userMakeVisitPage";
  }

  @PostMapping("/user/visit/doctor/{id}")
  public String getUserDoctorVisit(@ModelAttribute("visit")UserVisitDTO visitDTO, Model model, @PathVariable String id){
    int numId = Integer.parseInt(id);
    visitDTO.setDoctorId(numId);
    Surgery surgery = surgeryController.getSurgeryByName(visitDTO.getVisitName());
    WorkSchedule workSchedule = workScheduleController.workingThatDay(numId, visitDTO.getDate());
    List<Visit> visits = visitController.getVisitByDayAndDoctor(visitDTO.getDate(), numId);
    List<Intervals> intervals = prepareIntervals(surgery, workSchedule, visits);
    model.addAttribute("visit", visitDTO);
    model.addAttribute("doctors", null);
    model.addAttribute("doctor", userController.getUserById(numId));
    model.addAttribute("availableHours", intervals);
    return "userMakeVisitPage";
  }

  @PostMapping("/user/visit/make/{start}")
  public String addVisit(@ModelAttribute("visit")UserVisitDTO visitDTO, @PathVariable("start") String start,  Model model){
    LocalTime time = LocalTime.parse(start);
    Surgery surgery = surgeryController.getSurgeryByName(visitDTO.getVisitName());
    visitController.addVisit(visitDTO.getDate(), time, getUser().getId(),visitDTO.getDoctorId(), surgery.getSurgeryId());
    return "goBackToMain";
  }

  @GetMapping("/user/change")
  public String changeUser(Model model){
    User user = getUser();
    model.addAttribute("user", user);
    model.addAttribute("Message", null);
    return "userChangeUserPage";
  }

  @PostMapping("/user/change")
  public String postChangeUsersPage(@ModelAttribute("user") User user, Model model){
    userController.updateUser(user);
    model.addAttribute("user", user);
    StringDTO message = new StringDTO();
    if(userController.checkUser(user)){
      message.setValue("Action was successful!");
    }else{
      message.setValue("Action failed!");
    }
    model.addAttribute("message", message);
    return "userChangeUserPage";
  }

  @GetMapping("/cal/day/{i}")
  public String cal(@PathVariable("i") String i, Model model){
    return "visitDayPage";
  }

  @PostMapping("/system/delete/{username}")
  public void getByUsername(@PathVariable("username") String username, Model model){
    int userId = userController.getUserWithUsername(username).getId();
    userController.deleteUserWithId(userId);
  }

  @GetMapping("/user/surgeries")
  public String getUserSurgeries(Model model){
    List<Surgery> surgeries = surgeryController.getAllSurgeries();
    model.addAttribute("sur", surgeries);
    return "surgeriesPage";
  }

  @GetMapping("/user/appointments")
  public String getUserAppointments(Model model){
    User user = getUser();
    List<Visit> visits = visitController.getAllUserVisits(user.getId());
    List<FullVisitDTO> fullVisits = new ArrayList<>();
    for(Visit visit: visits){
      fullVisits.add(getFullVisitDTO(visit, userController, surgeryController));
    }
    model.addAttribute("visits", fullVisits);
    return "appointmentsPage";
  }

  @GetMapping("/user/remove/visit/{id}")
  public String removeVisit(@PathVariable("id") String id){
    int numId = Integer.parseInt(id);
    visitController.deleteVisitById(numId);
    return "goBackToMain";
  }

  public User getUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    return userController.getUserByUsername(currentPrincipalName);
  }

  public List<Intervals> prepareIntervals(Surgery surgery, WorkSchedule workSchedule, List<Visit> visits){
    List<Intervals> result = new ArrayList<>();

    LocalTime start = workSchedule.getStartTime();

    int res = start.compareTo(workSchedule.getEndTime());

    while (res < 0){
      boolean free = true;
      for(Visit visit: visits){
        if(start.equals(visit.getTime())){
          Surgery surg = surgeryController.getSurgeryById(visit.getSurgeryId());
          LocalTime end = start.plusMinutes(surg.getDuration());
          Intervals interval = new Intervals(start, end, "Reserved");
          result.add(interval);
          start=end;
          free = false;
        }
      }
      if(free){
        LocalTime end = start.plusMinutes(surgery.getDuration());
        Intervals interval = new Intervals(start, end, "Available");
        result.add(interval);
        start=end;
      }
      res = start.compareTo(workSchedule.getEndTime());
    }

    return result;
  }

  static FullVisitDTO getFullVisitDTO(Visit visit, UserController userController, SurgeryController surgeryController) {
    FullVisitDTO visitDTO = new FullVisitDTO();
    visitDTO.setId(visit.getVisitId());
    visitDTO.setClient(userController.getUserById(visit.getClientId()).getName());
    visitDTO.setDoctor(userController.getUserById(visit.getDoctorId()).getName());
    visitDTO.setDate(visit.getDate());
    visitDTO.setSurgery(surgeryController.getSurgeryById(visit.getSurgeryId()).getName());
    visitDTO.setStatus(visit.getStatus().toString());
    visitDTO.setTime(visit.getTime());
    visitDTO.setNotes(visit.getNotes());
    visitDTO.setProgress(visit.getProgressAssessment());
    return visitDTO;
  }
}
