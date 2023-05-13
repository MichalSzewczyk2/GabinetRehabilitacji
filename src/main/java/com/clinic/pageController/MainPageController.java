package com.clinic.pageController;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainPageController {

  @GetMapping("/main")
  public String getMain(@RequestParam(name = "page", required = false) String page) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    System.out.println(currentPrincipalName);
    if(currentPrincipalName.equals("admin")){
      return "mainAdminPage";
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

}