package com.mousephone.model.dto.customer;

import com.mousephone.model.*;
import com.mousephone.model.dto.customerAvatar.CustomerAvatarDTO;
import com.mousephone.model.dto.locationRegion.LocationRegionDTO;
import com.mousephone.model.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerDTO {
    private Long id;

    @NotEmpty(message = "Vui lòng nhập tên nhân viên.")
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5 - 100 ký tự")
    private String fullName;

    @NotEmpty(message = "Vui lòng nhập số điện thoại.")
    @Pattern(regexp = "^[+]*[(]?[0-9]{1,4}[)]?[-\\s./0-9]*$", message = "Số điện thoại không đúng định dạng.")
    private String phone;

    @Valid
    private LocationRegionDTO locationRegion;

    @Valid
    private UserDTO user;

    @Valid
    private CustomerAvatarDTO customerAvatar;

    public CustomerDTO(Long id, String fullName, String phone, LocationRegion locationRegion, User user, CustomerAvatar customerAvatar) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.locationRegion = locationRegion.toLocationRegionDTO();
        this.user = user.toUserDTO();
        this.customerAvatar = customerAvatar.toCustomerAvatarDTO();
    }

    public Customer toCustomer() {
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setLocationRegion(locationRegion.toLocationRegion())
                .setUser(user.toUser())
                .setCustomerAvatar(customerAvatar.toCustomerAvatar());
    }
}
