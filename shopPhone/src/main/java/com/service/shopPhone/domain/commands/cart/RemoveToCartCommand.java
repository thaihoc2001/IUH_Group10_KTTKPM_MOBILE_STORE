package com.service.shopPhone.domain.commands.cart;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.cart.RemoveToCartCommandInputModel;
import com.service.shopPhone.entity.CartDetailEntity;
import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.CartDetailRepository;
import com.service.shopPhone.repository.CartRepository;
import com.service.shopPhone.repository.ProductReponsitory;

@Service
public class RemoveToCartCommand implements ICommand<RemoveToCartCommandInputModel, StatusCommandResultModel>{
    
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final ProductReponsitory productReponsitory;

    public RemoveToCartCommand(
        CartDetailRepository cartDetailRepository, 
        CartRepository cartRepository,
        ProductReponsitory productReponsitory) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productReponsitory = productReponsitory;
    }

    @Override
    public StatusCommandResultModel execute(RemoveToCartCommandInputModel input){
        CartDetailEntity cartDetailEntity = cartDetailRepository.findById(input.getCartDetailId()).get();
        CartEntity cart = cartDetailEntity.getCart();
        ProductEntity product = cartDetailEntity.getProduct();
        cart.setCountProduct(cart.getCountProduct() - 1);
        cart.setTotalPrice(cart.getTotalPrice() - product.getPrice());
        cartRepository.save(cart);
        product.setQuantity(product.getQuantity() + 1);
        productReponsitory.save(product);
        if (cartDetailEntity.getQuantity() == 1) {
            cartDetailRepository.delete(cartDetailEntity);
        }else {
            cartDetailEntity.setQuantity(cartDetailEntity.getQuantity() - 1);
            cartDetailRepository.save(cartDetailEntity);
        }
        return StatusCommandResultModel.builder().success(true).build();
    }
}
