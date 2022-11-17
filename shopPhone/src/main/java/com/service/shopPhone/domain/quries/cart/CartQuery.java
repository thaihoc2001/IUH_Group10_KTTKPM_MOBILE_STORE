package com.service.shopPhone.domain.quries.cart;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.UserEntity;
import com.service.shopPhone.repository.CartRepository;

@Service
public class CartQuery {
    
    private final CartRepository cartRepository;

    @Autowired
    public CartQuery(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public CartEntity findCartByUser(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return cartRepository.findByUserAndIsActive(userEntity, true).get();
    }
}
