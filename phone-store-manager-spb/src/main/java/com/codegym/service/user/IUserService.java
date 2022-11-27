package com.codegym.service.user;

import com.codegym.model.User;
import com.codegym.service.IGeneralService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface IUserService extends IGeneralService<User>, UserDetailsService {

    User saveNoPassWord(User user);

    User getByUsername(String username);

    Optional<User> findByUserName(String username);

    Boolean existsByUsername(String username);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
