package com.mousephone.service.role;

import com.mousephone.model.Role;
import com.mousephone.model.dto.role.RoleDTO;
import com.mousephone.service.IGeneralService;

import java.util.List;

public interface IRoleService extends IGeneralService<Role> {
    List<RoleDTO> getAllRoleDTO();

    Role findByCode(String code);
}
