package com.mousephone.service.productAvatar;

import com.mousephone.model.ProductAvatar;
import com.mousephone.service.IGeneralService;

public interface IProductAvatarService extends IGeneralService<ProductAvatar> {
    void delete(String id);
}
