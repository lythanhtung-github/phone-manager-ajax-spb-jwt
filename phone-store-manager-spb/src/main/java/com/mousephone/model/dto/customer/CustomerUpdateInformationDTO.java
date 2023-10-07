package com.mousephone.model.dto.customer;

import com.mousephone.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerUpdateInformationDTO {
    private Long id;
    @NotEmpty(message = "Vui lòng nhập họ tên.")
    @Size(min = 5, max = 100, message = "Họ tên có độ dài nằm trong khoảng 5 - 100 ký tự.")
    private String fullName;
    @NotEmpty(message = "Vui lòng nhập số điện thoại.")
    private String phone;


    private String fileType;
    @Pattern(regexp = "^\\d+$", message = "ID Tỉnh/Thành phố phải là số.")
    @NotEmpty(message = "ID Tỉnh/Thành phố xã không được trống.")
    private String provinceId;
    @NotEmpty(message = "Tên Tỉnh/Thành phố không được trống.")
    private String provinceName;
    @Pattern(regexp = "^\\d+$", message = "ID Thành phố/Quận/Huyện phải là số.")
    @NotEmpty(message = "ID Thành phố/Quận/Huyện xã không được trống.")
    private String districtId;
    @NotEmpty(message = "Tên Thành phố/Quận/Huyện xã không được trống.")
    private String districtName;
    @Pattern(regexp = "^\\d+$", message = "ID Phường/Xã/Thị trấn phải là số.")
    @NotEmpty(message = "Phường/Xã/Thị trấn không được trống.")
    private String wardId;
    @NotEmpty(message = "Tên Phường/Xã/Thị trấn không được trống.")
    private String wardName;

    @NotEmpty(message = "Vui lòng nhập địa chỉ")
    @Size(min = 5, max = 100, message = "Địa chỉ có độ dài nằm trong khoảng 5 - 100 ký tự.")
    private String address;

    public LocationRegion toLocationRegion(){
        return new LocationRegion()
                .setId(id)
                .setProvinceId(provinceId)
                .setProvinceName(provinceName)
                .setDistrictId(districtId)
                .setDistrictName(districtName)
                .setWardId(wardId)
                .setWardName(wardName)
                .setAddress(address);
    }

    public Customer toCustomer(User user, LocationRegion locationRegion, CustomerAvatar customerAvatar){
        return new Customer()
                .setId(id)
                .setFullName(fullName)
                .setPhone(phone)
                .setLocationRegion(locationRegion)
                .setUser(user)
                .setCustomerAvatar(customerAvatar);
    }
}
