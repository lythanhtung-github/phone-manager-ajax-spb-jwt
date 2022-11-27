package com.codegym.service.productAvatar;

import com.codegym.model.ProductAvatar;
import com.codegym.service.IGeneralService;

public interface IProductAvatarService extends IGeneralService<ProductAvatar> {
    void delete(String id);
}
