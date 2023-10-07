package com.mousephone.controller;

import com.mousephone.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/cp/staffs")
public class StaffCPController {
    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ModelAndView showStaffList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("staff", appUtils.getStaff());
        modelAndView.setViewName("cp/staff/list");
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("staff", appUtils.getStaff());
        modelAndView.setViewName("cp/staff/create");
        return modelAndView;
    }

    @GetMapping("/view")
    public ModelAndView showViewForm() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("staff", appUtils.getStaff());
        modelAndView.setViewName("cp/staff/view");
        return modelAndView;
    }
}
