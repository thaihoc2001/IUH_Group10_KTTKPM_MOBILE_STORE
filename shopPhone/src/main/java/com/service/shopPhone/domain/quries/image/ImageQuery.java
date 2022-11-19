package com.service.shopPhone.domain.quries.image;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.shopPhone.entity.ImageEntity;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.ImageRepository;

@Service
public class ImageQuery {
    private final ImageRepository imageRepository;

    public ImageQuery(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public List<ImageEntity> getAllByProduct(UUID productId) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        return imageRepository.findByProduct(productEntity);
    }

    public ImageEntity getImageMainByProduct(UUID productId) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productId);
        return imageRepository.findByProductAndType(productEntity, "main");
    }
}
