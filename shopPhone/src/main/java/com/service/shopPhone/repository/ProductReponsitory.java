package com.service.shopPhone.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.ProductEntity;

@Repository
public interface ProductReponsitory extends JpaRepository<ProductEntity, UUID>{
    
    @Query(
        value = 
            "SELECT u from ProductEntity u WHERE (:searchText is NULL OR name LIKE %:searchText%) AND"
            + " u.isDeleted = false"
    )
    Page<ProductEntity> searchProduct(
        @Param("searchText") String searchText,
        Pageable pageable
    );
}
