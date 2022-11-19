package com.service.shopPhone.domain.services.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.commands.cart.AddCartCommand;
import com.service.shopPhone.domain.commands.cart.AddCartDetailCommand;
import com.service.shopPhone.domain.commands.cart.RemoveToCartCommand;
import com.service.shopPhone.domain.models.cart.AddCartCommandInputModel;
import com.service.shopPhone.domain.models.cart.AddCartDetailCommandInput;
import com.service.shopPhone.domain.models.cart.RemoveToCartCommandInputModel;
import com.service.shopPhone.domain.quries.cart.CartDetailQuery;
import com.service.shopPhone.domain.quries.cart.CartQuery;
import com.service.shopPhone.domain.quries.image.ImageQuery;
import com.service.shopPhone.domain.quries.user.UserQuery;
import com.service.shopPhone.entity.CartDetailEntity;
import com.service.shopPhone.entity.CartEntity;
import com.service.shopPhone.entity.ImageEntity;
import com.service.shopPhone.entity.UserEntity;
import com.service.shopPhone.models.cart.CartDetailResponseModel;
import com.service.shopPhone.models.cart.CartResponseModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartDomainSevice {
    private final AddCartCommand addCartCommand;
    private final UserQuery userQuery;
    private final AddCartDetailCommand addCartDetailCommand;
    private final CartQuery cartQuery;
    private final RemoveToCartCommand removeToCartCommand;
    private final CartDetailQuery cartDetailQuery;
    private final ImageQuery imageQuery;

    @Autowired
    public CartDomainSevice(
        AddCartCommand addCartCommand,
        UserQuery userQuery,
        AddCartDetailCommand addCartDetailCommand,
        CartQuery cartQuery,
        RemoveToCartCommand removeToCartCommand,
        ImageQuery imageQuery,
        CartDetailQuery cartDetailQuery
    ) {
        this.addCartCommand = addCartCommand;
        this.userQuery = userQuery;
        this.addCartDetailCommand = addCartDetailCommand;
        this.cartQuery = cartQuery;
        this.removeToCartCommand = removeToCartCommand;
        this.imageQuery = imageQuery;
        this.cartDetailQuery = cartDetailQuery;
    }

    public boolean createCart(String username) {
        UserEntity user = userQuery.getUserByUsername(username);
        return addCartCommand.execute(
            AddCartCommandInputModel.builder().userId(user.getId()).build()
        ).isSuccess();
    }

    public boolean addToCart(String username, int quantity, UUID productId) {
        UserEntity user = userQuery.getUserByUsername(username);
        CartEntity cartEntity = cartQuery.findCartByUser(user.getId());
        return addCartDetailCommand.execute(
            AddCartDetailCommandInput.builder().cartId(cartEntity.getId()).quantity(quantity).productId(productId).build()
        ).isSuccess();
    }

    public boolean removeToCart(UUID cartDetailId) {
        return removeToCartCommand.execute(
            RemoveToCartCommandInputModel.builder().cartDetailId(cartDetailId).build()
        ).isSuccess();
    }

    public CartResponseModel getCartByUser(String username) {
        UserEntity user = userQuery.getUserByUsername(username);
        CartEntity cart = cartQuery.findCartByUser(user.getId());
        List<CartDetailEntity> cartDetails = cartDetailQuery.getByCartId(cart.getId());
        List<CartDetailResponseModel> cartDetailResponseModels = new ArrayList<>();
        for (CartDetailEntity cartDetail : cartDetails) {
            ImageEntity image = imageQuery.getImageMainByProduct(cartDetail.getProduct().getId());
            CartDetailResponseModel result = CartDetailResponseModel.builder()
                .id(cartDetail.getId())
                .quantity(cartDetail.getQuantity())
                .productName(cartDetail.getProduct().getName())
                .productPrice(cartDetail.getProduct().getPrice())
                .productImage(image.getUrl())
                .productId(cartDetail.getProduct().getId())
                .build();
            cartDetailResponseModels.add(result);
        }

        CartResponseModel res = CartResponseModel.builder()
            .id(cart.getId())
            .countProduct(cart.getCountProduct())
            .totalPrice(cart.getTotalPrice())
            .cartDetails(cartDetailResponseModels)
            .build();

            return res;
    }
}
