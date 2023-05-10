package com.clinic.pageController;

import com.clinic.dbTables.User;
import com.clinic.exceptions.UserAlreadyExistException;
import com.clinic.helpers.UserDTO;
import com.clinic.helpers.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class signInController {

  @Autowired
  UserService userService;

  @GetMapping("/signIn")
  public String getSignIn(WebRequest request, Model model){
    UserDTO user = new UserDTO();
    model.addAttribute("user", user);
    return "signInPage";
  }

  @PostMapping("/signIn")
  public ModelAndView registerUserAccount(@Valid @ModelAttribute("user") UserDTO user, HttpServletRequest request, Errors errors){
    ModelAndView mav = new ModelAndView();
    try {
      User registered = userService.registerNewUserAccount(user);
    } catch (UserAlreadyExistException uaeEx) {
      mav.addObject("message", "An account for that username/email already exists.");
      return mav;
    }
    return new ModelAndView("successRegister", "user", user);
  }
}
