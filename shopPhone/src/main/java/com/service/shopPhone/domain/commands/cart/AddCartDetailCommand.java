package com.service.shopPhone.domain.commands.cart;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.cart.AddCartDetailCommandInput;
import com.service.shopPhone.entity.CartDetailEntity;
import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.ProductEntity;
import com.service.shopPhone.repository.CartDetailRepository;
import com.service.shopPhone.repository.CartRepository;
import com.service.shopPhone.repository.ProductReponsitory;

@Service
public class AddCartDetailCommand implements ICommand<AddCartDetailCommandInput, StatusCommandResultModel>{

    private final CartDetailRepository cartDetailRepository;
    private final CartRepository cartRepository;
    private final ProductReponsitory productReponsitory;

    public AddCartDetailCommand(
        CartDetailRepository cartDetailRepository, 
        CartRepository cartRepository,
        ProductReponsitory productReponsitory) {
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productReponsitory = productReponsitory;
    }

    @Override
    public StatusCommandResultModel execute(AddCartDetailCommandInput input) {
        CartEntity cart = cartRepository.findById(input.getCartId()).get();
        ProductEntity product = productReponsitory.findById(input.getProductId()).get();
        Double total = input.getQuantity() * product.getPrice();
        cart.setTotalPrice(cart.getTotalPrice() + total);
        cart.setCountProduct(cart.getCountProduct() + input.getQuantity());
        product.setQuantity(product.getQuantity() - input.getQuantity());
        cartRepository.save(cart);
        productReponsitory.save(product);
        CartDetailEntity cartDetailEntity = new CartDetailEntity();
        Optional<CartDetailEntity> check = cartDetailRepository.findByProductAndCart(product, cart);
        if (check.isPresent()) {
            cartDetailEntity = check.get();
            cartDetailEntity.setQuantity(check.get().getQuantity() + input.getQuantity());
        }else {
            cartDetailEntity.setId(UUID.randomUUID());
            cartDetailEntity.setCart(cart);
            cartDetailEntity.setProduct(product);
            cartDetailEntity.setQuantity(input.getQuantity());
        }
        cartDetailRepository.save(cartDetailEntity);
        return StatusCommandResultModel.builder().success(true).build();
    }
    
}
