package com.mousephone.service.customerAvatar;

import com.mousephone.model.CustomerAvatar;
import com.mousephone.service.IGeneralService;

public interface ICustomerAvatarService extends IGeneralService<CustomerAvatar> {
    void delete(String id);
}
