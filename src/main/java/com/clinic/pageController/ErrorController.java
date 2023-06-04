package com.clinic.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class ErrorController {

    @GetMapping("/back")
    public String getLogInPage(WebRequest request, Model model) {
        return "logInPage";
    }
}

