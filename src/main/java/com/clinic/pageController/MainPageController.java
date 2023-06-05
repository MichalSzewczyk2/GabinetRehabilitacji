package com.clinic.pageController;

import com.clinic.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {

  @Autowired
  private UserController userController;

  @GetMapping("/main")
  public String getMain(@RequestParam(name = "page", required = false) String page, Model model) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    String role = null;
    if(!currentPrincipalName.equals("anonymousUser")){
      role = userController.getUserByUsername(currentPrincipalName).getStringType();
    }
    if (role == null)role = "anonymousUser";

    model.addAttribute("role", role);
    System.out.println(role);
    if(currentPrincipalName.equals("admin")){
      return "mainAdminPage";
    }
    if(currentPrincipalName.equals("secretary")){
      return "mainSecretaryPage";
    }

    if(page != null){
      if(page.equals("home")){
        return "mainPage";
      }
      if(page.equals("contact")) {
        return "contactPage";
      }
    }
    return "mainPage";
  }

  @GetMapping("/customAccessDenied")
  public String customAccessDenied(Model model) {
    return "customAccessDenied";
  }

}
