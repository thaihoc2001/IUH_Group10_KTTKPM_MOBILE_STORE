package com.service.shopPhone.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID>{
    
}
