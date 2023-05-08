package com.clinic.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class mainPageController {

  @GetMapping("/main")
  public String getMain(@RequestParam(name = "page", required = false) String page) {

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
