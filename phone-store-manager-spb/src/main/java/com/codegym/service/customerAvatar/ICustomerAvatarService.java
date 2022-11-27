package com.codegym.service.customerAvatar;

import com.codegym.model.CustomerAvatar;
import com.codegym.service.IGeneralService;

public interface ICustomerAvatarService extends IGeneralService<CustomerAvatar> {
    void delete(String id);
}
