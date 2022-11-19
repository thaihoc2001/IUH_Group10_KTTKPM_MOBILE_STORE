package com.service.shopPhone.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, UUID>{
    
    @Query(
        value = 
            "SELECT u from OrderEntity u WHERE (:searchText is NULL OR cart.user.username LIKE %:searchText%) AND"
            + " (:status is NULL OR status=:status)"
    )
    Page<OrderEntity> searchOrder(
        @Param("searchText") String searchText,
        @Param("status") String status,
        Pageable pageable
    );
}
