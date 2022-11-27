package com.codegym.model.dto.role;

import com.codegym.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO {
    @NotNull(message = "Vui lòng chọn role")
    private Long id;

    private String code;

    public Role toRole() {
        return new Role()
                .setId(id)
                .setCode(code);
    }
}
