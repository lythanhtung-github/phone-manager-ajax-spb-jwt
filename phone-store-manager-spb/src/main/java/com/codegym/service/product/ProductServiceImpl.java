package com.codegym.service.product;

import com.codegym.exception.DataInputException;
import com.codegym.model.Product;
import com.codegym.model.ProductAvatar;
import com.codegym.model.dto.product.ProductCreateDTO;
import com.codegym.model.dto.product.ProductDTO;
import com.codegym.model.enums.FileType;
import com.codegym.repository.ProductRepository;
import com.codegym.service.productAvatar.IProductAvatarService;
import com.codegym.upload.IUploadService;
import com.codegym.utils.AppUtils;
import com.codegym.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Service
@Transactional
public class ProductServiceImpl implements IProductService{
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AppUtils appUtils;

    @Autowired
    private IUploadService uploadService;

    @Autowired
    private UploadUtil uploadUtil;

    @Autowired
    private IProductAvatarService productAvatarService;
    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<ProductDTO> getAllProductDTO() {
        return productRepository.getAllProductDTO();
    }
    @Override
    public Product getById(Long id) {
        return null;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {

    }
    @Override
    public boolean existsByProductName(String productName){
       return productRepository.existsByProductName(productName);
    }

    @Override
    public boolean existsByProductNameAndIdNot(String productName, Long id){
        return productRepository.existsByProductNameAndIdNot(productName, id);
    }

    @Override
    public Product createWithAvatar(ProductCreateDTO productCreateDTO){

        MultipartFile file = productCreateDTO.getFile();
        String fileType = file.getContentType();
        assert fileType != null;
        fileType = fileType.substring(0, 5);

        ProductAvatar productAvatar = new ProductAvatar();
        productAvatar.setFileType(fileType);
        productAvatar = productAvatarService.save(productAvatar);

        if (fileType.equals(FileType.IMAGE.getValue())) {
            productAvatar = uploadAndSaveProductAvatar(file, productAvatar);
        }
        Product product = productCreateDTO.toProduct(productAvatar);
        product = productRepository.save(product);
        return product;
    }

    @Override
    public Product saveWithAvatar(Product product, MultipartFile file) {

        ProductAvatar oldProductAvatar = product.getProductAvatar();
        try {
            uploadService.destroyImage(oldProductAvatar.getCloudId(), uploadUtil.buildImageDestroyParams(product, oldProductAvatar.getCloudId()));
            productAvatarService.delete(oldProductAvatar.getId());
            String fileType = file.getContentType();
            assert fileType != null;
            fileType = fileType.substring(0, 5);
            ProductAvatar productAvatar = new ProductAvatar();
            productAvatar.setFileType(fileType);
            productAvatar = productAvatarService.save(productAvatar);

            if (fileType.equals(FileType.IMAGE.getValue())) {
                productAvatar = uploadAndSaveProductAvatar(file, productAvatar);
            }
            product.setProductAvatar(productAvatar);
            product = productRepository.save(product);
            return product;
        } catch (IOException e) {
            throw new DataInputException("Xóa hình ảnh thất bại.");
        }
    }

    private ProductAvatar uploadAndSaveProductAvatar(MultipartFile file, ProductAvatar productAvatar) {
        try {
            Map uploadResult = uploadService.uploadImage(file, uploadUtil.buildImageUploadParams(productAvatar));
            String fileUrl = (String) uploadResult.get("secure_url");
            String fileFormat = (String) uploadResult.get("format");

            productAvatar.setFileName(productAvatar.getId() + "." + fileFormat);
            productAvatar.setFileUrl(fileUrl);
            productAvatar.setFileFolder(UploadUtil.PRODUCT_IMAGE_UPLOAD_FOLDER);
            productAvatar.setCloudId(productAvatar.getFileFolder() + "/" + productAvatar.getId());
            return productAvatarService.save(productAvatar);
        } catch (IOException e) {
            e.printStackTrace();
            throw new DataInputException("Upload hình ảnh thất bại.");
        }
    }

    @Override
    public void softDelete(Long productId) {
        productRepository.softDelete(productId);
    }
}
