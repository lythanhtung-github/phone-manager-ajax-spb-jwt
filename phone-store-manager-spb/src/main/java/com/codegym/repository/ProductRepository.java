package com.codegym.repository;

import com.codegym.model.Product;
import com.codegym.model.dto.product.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

    @Query("SELECT NEW com.codegym.model.dto.product.ProductDTO (" +
            "p.id, " +
            "p.productName, " +
            "p.price, " +
            "p.quantity, " +
            "p.description, " +
            "p.productAvatar " +
            ") " +
            "FROM Product AS p " +
            "WHERE p.deleted = false "
    )
    List<ProductDTO> getAllProductDTO();
    @Modifying
    @Query("UPDATE Product AS p SET p.deleted = true WHERE p.id = :productId")
    void softDelete(@Param("productId") long productId);

    boolean existsByProductName(String productName);

    Boolean existsByProductNameAndIdNot(String productName, Long id);
}
