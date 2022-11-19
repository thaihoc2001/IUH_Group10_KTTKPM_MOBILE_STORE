package com.service.shopPhone.domain.quries.cart;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.shopPhone.entity.CartDetailEntity;
import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.repository.CartDetailRepository;

@Service
public class CartDetailQuery {
    private final CartDetailRepository cartDetailRepository;

    public CartDetailQuery(CartDetailRepository cartDetailRepository) {
        this.cartDetailRepository = cartDetailRepository;
    }

    public List<CartDetailEntity> getByCartId(UUID cartId) {
        CartEntity cart = new CartEntity();
        cart.setId(cartId);
        return cartDetailRepository.findByCart(cart);
    }
}
