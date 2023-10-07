package com.mousephone.utils;

import com.cloudinary.utils.ObjectUtils;
import com.mousephone.exception.DataInputException;
import com.mousephone.model.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UploadUtil {
    public static final String STAFF_IMAGE_UPLOAD_FOLDER = "staff_images";
    public static final String PRODUCT_IMAGE_UPLOAD_FOLDER = "product_images";

    public static final String CUSTOMER_IMAGE_UPLOAD_FOLDER = "customer_images";

    public Map buildImageUploadParams(StaffAvatar staffAvatar) {
        if (staffAvatar == null || staffAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của nhân viên chưa được lưu");

        String publicId = String.format("%s/%s", STAFF_IMAGE_UPLOAD_FOLDER, staffAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageUploadParams(ProductAvatar productAvatar) {
        if (productAvatar == null || productAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của sản phẩm chưa được lưu");

        String publicId = String.format("%s/%s", PRODUCT_IMAGE_UPLOAD_FOLDER, productAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageUploadParams(CustomerAvatar customerAvatar) {
        if (customerAvatar == null || customerAvatar.getId() == null)
            throw new DataInputException("Không thể upload hình ảnh của khách hàng chưa được lưu");

        String publicId = String.format("%s/%s", CUSTOMER_IMAGE_UPLOAD_FOLDER, customerAvatar.getId());

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Staff staff, String publicId) {
        if (staff == null || staff.getId() == null)
            throw new DataInputException("Không thể destroy hình ảnh của nhân viên không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Product product, String publicId) {
        if (product == null || product.getId() == null)
            throw new DataInputException("Không thể destroy hình ảnh của sản phẩm không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }

    public Map buildImageDestroyParams(Customer customer, String publicId) {
        if (customer == null || customer.getId() == null)
            throw new DataInputException("Không thể destroy hình ảnh của khách hàng không xác định");

        return ObjectUtils.asMap(
                "public_id", publicId,
                "overwrite", true,
                "resource_type", "image"
        );
    }
}
