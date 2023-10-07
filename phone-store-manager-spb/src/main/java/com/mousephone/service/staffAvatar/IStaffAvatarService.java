package com.mousephone.service.staffAvatar;

import com.mousephone.model.StaffAvatar;
import com.mousephone.service.IGeneralService;

public interface IStaffAvatarService extends IGeneralService<StaffAvatar> {
    void delete(String id);
}
