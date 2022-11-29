package com.codegym.controller;

import com.codegym.service.customer.ICustomerService;
import com.codegym.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private AppUtils appUtils;

    @GetMapping
    public ModelAndView showHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customer", appUtils.getCustomer());
        modelAndView.setViewName("/customer/index");
        return modelAndView;
    }

    @GetMapping("/view")
    public ModelAndView showViewPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("customer", appUtils.getCustomer());
        modelAndView.setViewName("customer/view");
        return modelAndView;
    }
}
