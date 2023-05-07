package com.clinic.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainPageController {

  @GetMapping("/main")
  public String getMain(){
    return "mainPage";
  }

}
