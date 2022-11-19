package com.service.shopPhone.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.ImageEntity;
import com.service.shopPhone.entity.ProductEntity;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, UUID>{
    List<ImageEntity> findByProduct(ProductEntity product);
    ImageEntity findByProductAndType(ProductEntity product, String type);
}
