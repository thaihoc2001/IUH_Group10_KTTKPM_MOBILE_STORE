package com.service.shopPhone.domain.commands.order;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.ICommand;
import com.service.shopPhone.domain.models.StatusCommandResultModel;
import com.service.shopPhone.domain.models.order.AddOrderCommandInputModel;
import com.service.shopPhone.entity.AddressEntity;
import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.OrderEntity;
import com.service.shopPhone.entity.UserEntity;
import com.service.shopPhone.repository.AddressRepository;
import com.service.shopPhone.repository.CartRepository;
import com.service.shopPhone.repository.OrderRepository;
import com.service.shopPhone.repository.UserRepository;

@Service
public class AddOrderCommand implements ICommand<AddOrderCommandInputModel, StatusCommandResultModel>{
    
    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;

    public AddOrderCommand(
        CartRepository cartRepository,
        UserRepository userRepository,
        OrderRepository orderRepository,
        AddressRepository addressRepository
        ) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public StatusCommandResultModel execute(AddOrderCommandInputModel input) {
        UserEntity user = userRepository.findByUsername(input.getUsername());
        CartEntity cart = cartRepository.findByUserAndIsActive(user, true).get();
        AddressEntity address = addressRepository.findByUserAndIsActive(user, true);
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(UUID.randomUUID());
        orderEntity.setAddress(address);
        orderEntity.setCart(cart);
        orderEntity.setCreatedAt(OffsetDateTime.now());
        orderEntity.setTotalPrice(cart.getTotalPrice() - input.getCoupon());
        orderEntity.setNote(input.getNote());
        orderEntity.setTypePayment(input.getTypePayment());
        orderEntity.setStatus("pending");
        cart.setActive(false);
        cartRepository.save(cart);
        orderRepository.save(orderEntity);
        return StatusCommandResultModel.builder().success(true).build();
    }
}
