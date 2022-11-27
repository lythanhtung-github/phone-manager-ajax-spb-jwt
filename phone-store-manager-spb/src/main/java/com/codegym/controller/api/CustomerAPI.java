package com.codegym.controller.api;

import com.codegym.exception.DataInputException;
import com.codegym.model.Customer;
import com.codegym.model.dto.customer.CustomerDTO;
import com.codegym.service.customer.ICustomerService;
import com.codegym.service.role.IRoleService;
import com.codegym.service.user.IUserService;
import com.codegym.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {
    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private ICustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllByDeletedIsFalse() {
        List<CustomerDTO> customerDTOS = customerService.getAllCustomerDTO();
        if (customerDTOS.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerDTOS, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<?> getById(@PathVariable String customerId) {
        long sid;
        try {
            sid = Long.parseLong(customerId);
        } catch (NumberFormatException e) {
            throw new DataInputException("ID khách hàng không hợp lệ.");
        }

        Optional<Customer> customerOptional = customerService.findById(sid);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ.");
        }

        return new ResponseEntity<>(customerOptional.get().toCustomerDTO(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{customerId}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable Long customerId) {

        Optional<Customer> customerOptional = customerService.findById(customerId);

        if (!customerOptional.isPresent()) {
            throw new DataInputException("ID khách hàng không hợp lệ.");
        }

        try {
            customerService.softDelete(customerId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (Exception e) {
            throw new DataInputException("Vui lòng liên hệ Administrator.");
        }
    }
}
