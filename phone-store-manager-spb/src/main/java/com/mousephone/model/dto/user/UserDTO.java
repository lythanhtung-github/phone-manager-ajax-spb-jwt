package com.mousephone.model.dto.user;

import com.mousephone.model.User;
import com.mousephone.model.dto.role.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotBlank(message = "Vui lòng nhập email")
    @Email(message = "Email không đúng định dạng")
    @Size(min = 5, max = 50, message = "Độ dài email trong khoảng 5-50 ký tự")
    private String username;

    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(max = 30, message = "Độ dài mật khẩu tối đa 30 ký tự")
    private String password;

    @Valid
    private RoleDTO role;

    public UserDTO(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public User toUser() {
        return new User()
                .setId(id)
                .setUsername(username)
                .setPassword(password)
                .setRole(role.toRole());
    }
}
