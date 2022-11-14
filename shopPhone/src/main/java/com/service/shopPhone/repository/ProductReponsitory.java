package com.service.shopPhone.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.ProductEntity;

@Repository
public interface ProductReponsitory extends JpaRepository<ProductEntity, UUID>{
    
}
