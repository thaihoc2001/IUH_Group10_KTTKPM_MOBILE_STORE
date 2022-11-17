package com.service.shopPhone.service.cart;

import java.util.UUID;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.cart.AddToCartRequestModel;

public interface ICartService {
    public Response<StatusResponseModel> createCart(String username);
    public Response<StatusResponseModel> addToCart(String username, AddToCartRequestModel requestModel);
    public Response<StatusResponseModel> removeToCart(UUID cartDetailId);
}
