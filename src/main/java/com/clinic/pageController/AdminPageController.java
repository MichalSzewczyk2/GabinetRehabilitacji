package com.clinic.pageController;

import com.clinic.controller.SurgeryController;
import com.clinic.controller.UserController;
import com.clinic.controller.VisitController;
import com.clinic.controller.WorkScheduleController;
import com.clinic.dbTables.User;
import com.clinic.dbTables.Visit;
import com.clinic.dbTables.WorkSchedule;
import com.clinic.dto.EmployeeDTO;
import com.clinic.dto.StringDTO;
import com.clinic.dto.VisitDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

import static com.clinic.pageController.SecretaryController.getVisitDTO;

@Controller
public class AdminPageController {

  @Autowired
  private UserController userController;

  @Autowired
  private VisitController visitController;

  @Autowired
  private WorkScheduleController workScheduleController;

  @Autowired
  private SurgeryController surgeryController;

  @GetMapping("/admin/users")
  public String getManageUsersPage(Model model){
    User user = new User();
    StringDTO username = new StringDTO();
    model.addAttribute("user", user);
    model.addAttribute("username", username);
    model.addAttribute("users", userController.getAll());
    return "manageUsersPage";
  }

  @GetMapping("/admin/employees")
  public String getEmployeesPage(Model model){
    List<EmployeeDTO> employees = new ArrayList<>();
    List<User> userEmployees = userController.getEmployees();
    for(User employee : userEmployees){
      EmployeeDTO emp = new EmployeeDTO();
      emp.setId(employee.getId());
      emp.setName(employee.getName() + " " + employee.getSurname());
      employees.add(emp);
    }
    model.addAttribute("employees", employees);
    return "manageEmployeesPage";
  }

  @GetMapping("/admin/visits")
  public String getVisitsPage(Model model){
    StringDTO username = new StringDTO();
    model.addAttribute("username", username);
    return "manageVisitsPage";
  }

  @PostMapping("/admin/visits")
  public String postManageVisitsPage(@ModelAttribute("username")StringDTO username, Model model){
    int userId = userController.getUserByUsername(username.getValue()).getId();
    List<Visit> visits = visitController.getAllUserVisits(userId);
    List<VisitDTO> visitsDTO = new ArrayList<>();
    for(Visit visit: visits){
      visitsDTO.add(solveVisit(visit));
    }
    StringDTO newUsername = new StringDTO();
    model.addAttribute("username", newUsername);
    model.addAttribute("visits", visitsDTO);
    return "manageVisitsPage";
  }

  @GetMapping("/admin/user/change/{id}")
  public String changeUser(@PathVariable("id")String id, Model model){
    int numberId = Integer.parseInt(id);
    User user = userController.getUserById(numberId);
    model.addAttribute("user", user);
    model.addAttribute("Message", null);
    return "changeUserPage";
  }

  @GetMapping("/admin/employee/change/{id}")
  public String changeEmployee(@PathVariable("id")String id, Model model){
    int numberId = Integer.parseInt(id);
    User user = userController.getUserById(numberId);
    WorkSchedule schedule = new WorkSchedule();
    model.addAttribute("user", user);
    model.addAttribute("newSchedule", schedule);
    model.addAttribute("schedules", workScheduleController.getSchedulesForUser(numberId));
    return "employeePage";
  }

  @PostMapping("/admin/user")
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
    return "changeUserPage";
  }

  @PostMapping("/admin/user/byUsername")
  public String getByUsername(@ModelAttribute("username") StringDTO username, Model model){
    User user = userController.getUserWithUsername(username.getValue());
    model.addAttribute("user", user);
    return "changeUserPage";
  }

  @GetMapping("/admin/user/deleteUser/{id}")
  public String deleteUser(@PathVariable("id")String id, Model model){
    int numberId = Integer.parseInt(id);
    userController.deleteUserWithId(numberId);
    User user = new User();
    StringDTO username = new StringDTO();
    model.addAttribute("user", user);
    model.addAttribute("username", username);
    model.addAttribute("users", userController.getAll());
    return "manageUsersPage";
  }

  @GetMapping("/admin/visit/deleteVisit/{id}")
  public String deleteVisit(@PathVariable("id")String id, @ModelAttribute("username")StringDTO username, Model model){
    int numberId = Integer.parseInt(id);
    visitController.deleteVisitById(numberId);
    int userId = userController.getUserByUsername(username.getValue()).getId();
    List<Visit> visits = visitController.getAllUserVisits(userId);
    List<VisitDTO> visitsDTO = new ArrayList<>();
    for(Visit visit: visits){
      visitsDTO.add(solveVisit(visit));
    }
    StringDTO newUsername = new StringDTO();
    model.addAttribute("username", newUsername);
    model.addAttribute("visits", visitsDTO);
    return "manageVisitsPage";
  }

  @GetMapping("/admin/visit/change/{id}")
  public String changeVisit(@PathVariable("id")String id, Model model){
    int numberId = Integer.parseInt(id);
    visitController.getVisitById(numberId);
    model.addAttribute("visit", visitController.getVisitById(numberId));
    return "changeVisitPage";
  }

  @GetMapping("/admin/schedule/change/{id}")
  public String changeSchedule(@PathVariable("id") String id, Model model){
    WorkSchedule workSchedule = workScheduleController.getWorkScheduleBuId(Integer.parseInt(id));
    model.addAttribute("schedule", workSchedule);
    return "changeSchedulePage";
  }

  @GetMapping("/admin/schedule/delete/{id}")
  public String deleteSchedule(@PathVariable("id") String id){
    workScheduleController.deleteWorkSchedule(Integer.parseInt(id));
    return "goBackToMain";
  }


  public VisitDTO solveVisit(Visit visit){
    return getVisitDTO(visit, userController, surgeryController);
  }
}
