package com.mousephone.model.dto.user;

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
public class UserChangePassWordDTO {
    private Long id;

    @NotEmpty(message = "Nhân viên không hợp lệ.")
    private String userName;

    @NotEmpty(message = "Vui lòng nhập mật khẩu mới.")
    @Size(min = 6, max = 100, message = "Mật khẩu có độ dài nằm trong khoảng 6 - 100 ký tự.")
    private String newPassword;

    @NotEmpty(message = "Vui lòng nhập lại mật khẩu mới.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Mật khẩu không đúng định dạng (Mật khẩu gồm 1 ít nhất ký tự hoa, thường, số, ký tự đặc biệt)")
    @Size(min = 8, max = 100, message = "Mật khẩu có độ dài nằm trong khoảng 8 - 100 ký tự.")
    private String newPasswordConfirm;
}
