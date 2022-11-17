package com.service.shopPhone.domain.services.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.order.AddOrderCommand;
import com.service.shopPhone.domain.models.order.AddOrderCommandInputModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderDomainService {
    private final AddOrderCommand addOrderCommand;

    @Autowired
    public OrderDomainService(AddOrderCommand addOrderCommand) {
        this.addOrderCommand = addOrderCommand;
    }

    public boolean createOrder(String username, Double coupon, String typePayment, String note) {
        return addOrderCommand.execute(
            AddOrderCommandInputModel.builder().username(username).coupon(coupon).typePayment(typePayment).note(note).build()
        ).isSuccess();
    }
}
