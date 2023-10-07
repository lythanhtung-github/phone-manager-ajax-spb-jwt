package com.mousephone.repository;

import com.mousephone.model.Role;
import com.mousephone.model.dto.role.RoleDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT NEW com.mousephone.model.dto.role.RoleDTO(" +
            "r.id, " +
            "r.code" +
            ") " +
            "FROM Role AS r " +
            "WHERE r.code <> 'CUSTOMER'"
    )
    List<RoleDTO> getAllRoleDTO();

    Role findByCode(String code);
}
