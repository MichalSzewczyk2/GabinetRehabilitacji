package com.clinic.pageController;

import com.clinic.controller.SurgeryController;
import com.clinic.controller.UserController;
import com.clinic.controller.VisitController;
import com.clinic.controller.WorkScheduleController;
import com.clinic.dbTables.User;
import com.clinic.dbTables.Visit;
import com.clinic.dbTables.WorkSchedule;
import com.clinic.dto.EmployeeDTO;
import com.clinic.dto.NameSurnameDTO;
import com.clinic.dto.StringDTO;
import com.clinic.dto.VisitDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SecretaryController {

  @Autowired
  private UserController userController;

  @Autowired
  private VisitController visitController;

  @Autowired
  private SurgeryController surgeryController;

  @Autowired
  private WorkScheduleController workScheduleController;

  @GetMapping("/secretary/getContact")
  public String getContact(WebRequest request, Model model){
    model.addAttribute("name", new NameSurnameDTO());
    return "getUserContactPage";
  }

  @PostMapping("/secretary/getContact")
  public String postContact(@ModelAttribute("name")NameSurnameDTO name, WebRequest request, Model model){
    model.addAttribute("name", new NameSurnameDTO());
    List<User> users = userController.getUserListByNameAndSurname(name.getName(), name.getSurname());
    model.addAttribute("users", users);
    return "getUserContactPage";
  }

  @GetMapping("/secretary/visits")
  public String getVisitsPage(WebRequest request,Model model){
    StringDTO username = new StringDTO();
    model.addAttribute("username", username);
    return "secretaryVisitPage";
  }

  @PostMapping("/secretary/visits")
  public String postManageVisitsPage(@ModelAttribute("username")StringDTO username, Model model){
    int userId = userController.getUserByUsername(username.getValue()).getId();
    List<Visit> visits = visitController.getAllUserVisits(userId);
    List<VisitDTO> visitsDTO = new ArrayList<>();
    for(Visit visit: visits){
      visitsDTO.add(solveVisit(visit));
    }
    model.addAttribute("username", new StringDTO());
    model.addAttribute("visits", visitsDTO);
    return "secretaryVisitPage";
  }

  @GetMapping("/secretary/employees")
  public String getEmployeesPage(WebRequest request, Model model){
    List<EmployeeDTO> employees = new ArrayList<>();
    List<User> userEmployees = userController.getEmployees();
    for(User employee : userEmployees){
      EmployeeDTO emp = new EmployeeDTO();
      emp.setId(employee.getId());
      emp.setName(employee.getName() + " " + employee.getSurname());
      employees.add(emp);
    }
    model.addAttribute("employees", employees);
    return "secretaryEmployeePage";
  }

  @GetMapping("/secretary/employee/change/{id}")
  public String changeEmployee(@PathVariable("id")String id, Model model){
    int numberId = Integer.parseInt(id);
    User user = userController.getUserById(numberId);
    WorkSchedule schedule = new WorkSchedule();
    model.addAttribute("user", user);
    model.addAttribute("newSchedule", schedule);
    model.addAttribute("schedules", workScheduleController.getSchedulesForUser(numberId));
    return "secretaryChangeEmployee";
  }

  public VisitDTO solveVisit(Visit visit){
    return getVisitDTO(visit, userController, surgeryController);
  }

  @NotNull
  static VisitDTO getVisitDTO(Visit visit, UserController userController, SurgeryController surgeryController) {
    VisitDTO visitDTO = new VisitDTO();
    visitDTO.setId(visit.getVisitId());
    visitDTO.setClient(userController.getUserById(visit.getClientId()).getName());
    visitDTO.setDoctor(userController.getUserById(visit.getDoctorId()).getName());
    visitDTO.setDate(visit.getDate());
    visitDTO.setSurgery(surgeryController.getSurgeryById(visit.getSurgeryId()).getName());
    visitDTO.setStatus(visit.getStatus().toString());
    return visitDTO;
  }

}
