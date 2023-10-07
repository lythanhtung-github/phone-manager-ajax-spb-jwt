package com.mousephone.service.customer;

import com.mousephone.model.Customer;
import com.mousephone.model.LocationRegion;
import com.mousephone.model.User;
import com.mousephone.model.dto.customer.CustomerCreateDTO;
import com.mousephone.model.dto.customer.CustomerDTO;
import com.mousephone.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ICustomerService extends IGeneralService<Customer> {
    Customer saveWithLocationRegion(Customer customer);

    List<CustomerDTO> getAllCustomerDTO();

    Optional<CustomerDTO> getByEmailDTO(String email);

    Optional<Customer> findByPhone(String phone);

    void softDelete(Long customerId);

    Boolean existsByPhoneAndIdNot(String phone, Long id);

    Customer createCustomerWithAvatar(CustomerCreateDTO customerCreateDTO,
                                      LocationRegion locationRegion,
                                      User user);

    Customer saveWithAvatar(Customer customer, MultipartFile file);
}
