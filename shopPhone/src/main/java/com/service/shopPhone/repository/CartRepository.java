package com.service.shopPhone.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.UserEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID>{
    Optional<CartEntity> findByUserAndIsActive(UserEntity user, Boolean isActive);
}
