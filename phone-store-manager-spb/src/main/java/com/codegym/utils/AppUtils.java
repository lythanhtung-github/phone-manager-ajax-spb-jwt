package com.codegym.utils;

import com.codegym.model.dto.customer.CustomerDTO;
import com.codegym.model.dto.staff.StaffDTO;
import com.codegym.model.dto.user.UserDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.staff.IStaffService;
import com.codegym.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AppUtils {
    @Autowired
    private IUserService userService;

    @Autowired
    private IStaffService staffService;

    @Autowired
    private ICustomerService customerService;

    public ResponseEntity<?> mapErrorToResponse(BindingResult result) {
        List<FieldError> fieldErrors = result.getFieldErrors();
        Map<String, String> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    public StaffDTO getStaff() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        Optional<StaffDTO> staffDTOOptional = staffService.getByEmailDTO(userName);
        return staffDTOOptional.get();
    }

    public CustomerDTO getCustomer() {
        String userName;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        Optional<CustomerDTO> customerDTOOptional = customerService.getByEmailDTO(userName);
        return customerDTOOptional.get();
    }
}
