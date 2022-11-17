package com.service.shopPhone.service.cart;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.shopPhone.domain.services.cart.CartDomainSevice;
import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.cart.AddToCartRequestModel;
import com.service.shopPhone.utility.RequestCorrelation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CartService implements ICartService{

    private final CartDomainSevice cartDomainSevice;

    @Autowired
    public CartService(CartDomainSevice cartDomainSevice){
        this.cartDomainSevice = cartDomainSevice;
    }

    @Override
    public Response<StatusResponseModel> createCart(String username) {
        boolean result = cartDomainSevice.createCart(username);
        return Response.<StatusResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(StatusResponseModel.builder().success(result).build())
            .build();
    }

    @Override
    public Response<StatusResponseModel> addToCart(String username, AddToCartRequestModel requestModel) {
        boolean result = cartDomainSevice.addToCart(username, requestModel.getQuantity(), requestModel.getProductId());
        return Response.<StatusResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(StatusResponseModel.builder().success(result).build())
            .build();
    }

    @Override
    public Response<StatusResponseModel> removeToCart(UUID cartDetailId) {
        boolean result = cartDomainSevice.removeToCart(cartDetailId);
        return Response.<StatusResponseModel>builder()
            .id(RequestCorrelation.getRequestId())
            .data(StatusResponseModel.builder().success(result).build())
            .build();
    }
    
}
