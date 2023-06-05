package com.clinic.pageController;

import com.clinic.controller.UserController;
import com.clinic.dbTables.User;
import com.clinic.dbTables.Visit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserPageController {

  @Autowired
  private UserController userController;
  @GetMapping("/user/profile")
  public String getUserProfile(Model model){
    User user = getUser();
    model.addAttribute("user", user);
    return "userProfilePage";
  }

//  @GetMapping("/user/visit")
//  public String getUserVisit(Model model){
//    User user = getUser();
//
//    model.addAttribute("user", user);
//    model.addAttribute("visit", new Visit());
//    return "userVisitPage";
//  }

  @GetMapping("/cal/day/{i}")
  public String cal(@PathVariable("i") String i, Model model){
    return "visitDayPage";
  }

  public User getUser(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    return userController.getUserByUsername(currentPrincipalName);
  }

  @PostMapping("/system/delete/{username}")
  public void getByUsername(@PathVariable("username") String username, Model model){
    int userId = userController.getUserWithUsername(username).getId();
    userController.deleteUserWithId(userId);
  }
}
