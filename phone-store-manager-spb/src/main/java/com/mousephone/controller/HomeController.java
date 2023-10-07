package com.mousephone.controller;

import com.mousephone.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private AppUtils appUtils;

    @GetMapping("")
    public ModelAndView showHomePage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/home");
        return modelAndView;
    }

    @GetMapping("/")
    public String showIndex() {
        return "/home";
    }

    @GetMapping("/cp")
    public ModelAndView showControlPanelIndex() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("cp/index");

        modelAndView.addObject("staff", appUtils.getStaff());

        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView login() {

        return new ModelAndView("login");
    }

    @GetMapping("/register")
    public ModelAndView register() {

        return new ModelAndView("register");
    }
}
