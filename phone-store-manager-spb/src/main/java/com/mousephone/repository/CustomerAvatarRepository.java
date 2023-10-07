package com.mousephone.repository;

import com.mousephone.model.CustomerAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerAvatarRepository extends JpaRepository<CustomerAvatar, String> {
}
