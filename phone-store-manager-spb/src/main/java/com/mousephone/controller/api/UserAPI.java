package com.mousephone.controller.api;

import com.mousephone.exception.DataInputException;
import com.mousephone.model.User;
import com.mousephone.model.dto.user.UserChangePassWordDTO;
import com.mousephone.service.user.IUserService;
import com.mousephone.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    @Autowired
    private IUserService userService;

    @Autowired
    private AppUtils appUtils;

    @PatchMapping("/change-password")
    public ResponseEntity<?> changePassWord(@Validated UserChangePassWordDTO userChangePassWordDTO, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return appUtils.mapErrorToResponse(bindingResult);
        }
        Optional<User> userOptional = userService.findByUserName(userChangePassWordDTO.getUserName());

        if (!userOptional.isPresent()) {
            throw new DataInputException("User không tồn tại.");
        }
        User user = userOptional.get();


        String newPassWord = userChangePassWordDTO.getNewPassword();
        String newPassWordConfirm = userChangePassWordDTO.getNewPasswordConfirm();

        if(!newPassWord.equals(newPassWordConfirm)){
            throw new DataInputException("Mật khẩu mới không khớp nhau. Vui lòng nhập lại.");
        }

        user.setPassword(newPassWord);
        userService.save(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
