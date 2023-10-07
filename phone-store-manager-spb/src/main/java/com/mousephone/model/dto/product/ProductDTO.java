package com.mousephone.model.dto.product;

import com.mousephone.model.Product;
import com.mousephone.model.ProductAvatar;
import com.mousephone.model.dto.productAvatar.ProductAvatarDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO{
    private Long id;

    private String productName;
    private BigDecimal price;
    private int quantity;

    private String description;

    private ProductAvatarDTO productAvatar;

    public ProductDTO(Long id, String productName, BigDecimal price, int quantity, String description, ProductAvatar productAvatar) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.productAvatar = productAvatar.productAvatarDTO();
    }

    public Product toProduct(){
        return new Product()
                .setId(id)
                .setProductName(productName)
                .setPrice(price)
                .setQuantity(quantity)
                .setDescription(description)
                .setProductAvatar(productAvatar.toProductAvatarDTO());
    }
}
