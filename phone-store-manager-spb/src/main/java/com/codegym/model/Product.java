package com.codegym.model;

import com.codegym.model.dto.product.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(precision = 12, scale = 0, nullable = false)
    private BigDecimal price;

    private int quantity;

    private String description;

    @OneToOne
    @JoinColumn(name = "product_avatar_id")
    private ProductAvatar productAvatar;

    public ProductDTO toProductDTO(){
        return new ProductDTO()
               .setId(id)
                .setProductName(productName)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description)
                .setProductAvatar(productAvatar.productAvatarDTO());
    }
}
