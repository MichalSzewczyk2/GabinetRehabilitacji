package com.clinic.pageController;

import com.clinic.controller.SurgeryController;
import com.clinic.controller.UserController;
import com.clinic.dbTables.Surgery;
import com.clinic.dbTables.User;
import com.clinic.dbTables.Visit;
import com.clinic.dto.StringDTO;
import com.clinic.dto.UserVisitDTO;
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

import java.util.List;

@Controller
public class UserPageController {

  @Autowired
  private UserController userController;
  @Autowired
  private SurgeryController surgeryController;

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
    return "userMakeVisitPage";
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

  public User getUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    return userController.getUserByUsername(currentPrincipalName);
  }
}
