package com.clinic.pageController;

import com.clinic.controller.UserController;
import com.clinic.dbTables.User;
import com.clinic.helpers.StringDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminPageController {

  @Autowired
  private UserController userController;

  @GetMapping("/admin/users")
  public String getManageUsersPage(WebRequest request, Model model){
    User user = new User();
    StringDTO username = new StringDTO();
    model.addAttribute("user", user);
    model.addAttribute("username", username);
    model.addAttribute("users", userController.getAll());
    return "manageUsersPage";
  }

  @GetMapping("/admin/employees")
  public String getEmployeesPage(WebRequest request, Model model){
    return "manageEmployeesPage";
  }

  @GetMapping("/admin/visits")
  public String getVisitsPage(WebRequest request,Model model){
    StringDTO username = new StringDTO();
    model.addAttribute("username", username);
    return "manageVisitsPage";
  }

  @GetMapping("/admin/user/change/{id}")
  public String changeUser(@PathVariable("id")String id, Model model){
    int numberId = Integer.parseInt(id);
    User user = userController.getUserById(numberId);
    model.addAttribute("user", user);
    return "changeUserPage";
  }
  @PostMapping("/admin/user")
  public ModelAndView postChangeUsersPage(@ModelAttribute("user") User user, WebRequest request){
    ModelAndView mav = new ModelAndView();
    userController.updateUser(user);
    return new ModelAndView("succcess");
  }

  @PostMapping("/admin/user/byUsername")
  public String getByUsername(@ModelAttribute("username") StringDTO username, Model model){
    User user = userController.getUserWithUsername(username.getValue());
    model.addAttribute("user", user);
    return "changeUserPage";
  }

}
