package com.codegym.service.productAvatar;

import com.codegym.model.ProductAvatar;
import com.codegym.repository.ProductAvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ProductAvatarImpl implements IProductAvatarService{

    @Autowired
    private ProductAvatarRepository productAvatarRepository;

    @Override
    public List<ProductAvatar> findAll() {
        return null;
    }

    @Override
    public ProductAvatar getById(Long id) {
        return null;
    }

    @Override
    public Optional<ProductAvatar> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ProductAvatar save(ProductAvatar productAvatar) {
        return productAvatarRepository.save(productAvatar);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void delete(String id) {
        productAvatarRepository.deleteById(id);
    }
}
