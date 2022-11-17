package com.service.shopPhone.service.cart;

import java.util.UUID;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;

public interface ICartService {
    public Response<StatusResponseModel> createCart(String username);
}
