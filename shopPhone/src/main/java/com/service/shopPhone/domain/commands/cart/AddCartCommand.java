package com.service.shopPhone.domain.commands.cart;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.cart.AddCartCommandInputModel;
import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.UserEntity;
import com.service.shopPhone.repository.CartRepository;

@Service
public class AddCartCommand implements ICommand<AddCartCommandInputModel, StatusCommandResultModel>{

    private final CartRepository cartRepository;

    public AddCartCommand(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public StatusCommandResultModel execute(AddCartCommandInputModel input) {
        UserEntity user = new UserEntity();
        user.setId(input.getUserId());
        Optional<CartEntity> cartOptional = cartRepository.findByUserAndIsActive(user, true);
        if (cartOptional.isPresent()) {
            CartEntity entity = cartOptional.get();
            entity.setActive(false);
            cartRepository.save(entity);
        }
        CartEntity newCartEntity = new CartEntity();
        newCartEntity.setId(UUID.randomUUID());
        newCartEntity.setActive(true);
        newCartEntity.setCountProduct(0);
        newCartEntity.setTotalPrice(0.0);
        newCartEntity.setUser(user);
        newCartEntity.setCreatedAt(OffsetDateTime.now());
        cartRepository.save(newCartEntity);

        return StatusCommandResultModel.builder().success(true).build();
    }
    
}
