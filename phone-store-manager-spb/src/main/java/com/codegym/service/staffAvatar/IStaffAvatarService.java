package com.codegym.service.staffAvatar;

import com.codegym.model.StaffAvatar;
import com.codegym.service.IGeneralService;

public interface IStaffAvatarService extends IGeneralService<StaffAvatar> {
    void delete(String id);
}
