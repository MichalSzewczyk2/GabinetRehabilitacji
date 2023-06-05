package com.clinic.pageController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
    DO PREZENTACJI 500!
*/
@Controller
public class PressentationController {

    @GetMapping("/500")
    public String getMain(@RequestParam(name = "page", required = false) String page, Model model) throws Exception {
        throw new NullPointerException();
    }
}
