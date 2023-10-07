package com.mousephone.service.product;

import com.mousephone.model.Product;
import com.mousephone.model.dto.product.ProductCreateDTO;
import com.mousephone.model.dto.product.ProductDTO;
import com.mousephone.service.IGeneralService;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    List<ProductDTO> getAllProductDTO();

    boolean existsByProductName(String productName);

    boolean existsByProductNameAndIdNot(String productName, Long id);

    Product createWithAvatar(ProductCreateDTO productCreateDTO);

    Product saveWithAvatar(Product product, MultipartFile file);

    void softDelete(Long staffId);
}
