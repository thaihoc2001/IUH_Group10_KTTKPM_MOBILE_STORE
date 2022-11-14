package com.service.shopPhone.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.BrandEntity;

@Repository
public interface BrandReponsitory extends JpaRepository<BrandEntity, UUID>{
    
    @Query(
        value = 
            "SELECT u from BrandEntity u WHERE (:searchText is NULL OR name LIKE %:searchText%) AND"
            + " u.isDeleted = false"
    )
    Page<BrandEntity> searchBrand(
        @Param("searchText") String searchText,
        Pageable pageable
    );

    Optional<BrandEntity> findByName(String name);
}
