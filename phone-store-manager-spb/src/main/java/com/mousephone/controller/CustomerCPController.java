package com.mousephone.controller;

import com.mousephone.model.Customer;
import com.mousephone.service.customer.ICustomerService;
import com.mousephone.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/cp/customers")
public class CustomerCPController {
    @Autowired
    private AppUtils appUtils;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ModelAndView showCustomerList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("staff", appUtils.getStaff());
        modelAndView.setViewName("cp/customer/list");
        return modelAndView;
    }

    @GetMapping("/view/{customerId}")
    public ModelAndView showViewCustomer(@PathVariable Long customerId) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Customer> customerOptional = customerService.findById(customerId);

        Customer customer = customerOptional.get();
        modelAndView.addObject("customer", customer);

        modelAndView.addObject("staff", appUtils.getStaff());
        modelAndView.setViewName("cp/customer/view");
        return modelAndView;
    }
}
