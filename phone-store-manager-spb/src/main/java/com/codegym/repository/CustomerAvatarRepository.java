package com.codegym.repository;

import com.codegym.model.CustomerAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAvatarRepository extends JpaRepository<CustomerAvatar, String> {
}
