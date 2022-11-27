package com.codegym.service.role;

import com.codegym.model.Role;
import com.codegym.model.dto.role.RoleDTO;
import com.codegym.service.IGeneralService;

import java.util.List;

public interface IRoleService extends IGeneralService<Role> {
    List<RoleDTO> getAllRoleDTO();

    Role findByCode(String code);
}
