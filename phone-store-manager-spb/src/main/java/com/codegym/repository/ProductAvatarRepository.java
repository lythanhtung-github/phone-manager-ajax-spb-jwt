package com.codegym.repository;

import com.codegym.model.ProductAvatar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAvatarRepository extends JpaRepository<ProductAvatar, String> {
}
