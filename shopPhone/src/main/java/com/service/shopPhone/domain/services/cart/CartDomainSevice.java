package com.service.shopPhone.domain.services.cart;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.cart.AddCartCommand;
import com.service.shopPhone.domain.models.cart.AddCartCommandInputModel;
import com.service.shopPhone.domain.quries.user.UserQuery;
import com.service.shopPhone.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartDomainSevice {
    private final AddCartCommand addCartCommand;
    private final UserQuery userQuery;

    @Autowired
    public CartDomainSevice(
        AddCartCommand addCartCommand,
        UserQuery userQuery
    ) {
        this.addCartCommand = addCartCommand;
        this.userQuery = userQuery;
    }

    public boolean createCart(String username) {
        UserEntity user = userQuery.getUserByUsername(username);
        return addCartCommand.execute(
            AddCartCommandInputModel.builder().userId(user.getId()).build()
        ).isSuccess();
    }
}
