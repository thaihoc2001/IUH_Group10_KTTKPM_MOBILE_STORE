package com.service.shopPhone.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.shopPhone.entity.CartDetailEntity;
import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.ProductEntity;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetailEntity, UUID>{
    Optional<CartDetailEntity> findByProductAndCart(ProductEntity product, CartEntity cart);
    List<CartDetailEntity> findByCart(CartEntity cart);
}
