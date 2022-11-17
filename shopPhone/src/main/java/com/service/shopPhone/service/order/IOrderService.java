package com.service.shopPhone.service.order;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.order.CreateOrderRequestModel;

public interface IOrderService {
    public Response<StatusResponseModel> createOrder(String username,CreateOrderRequestModel requestModel);
}
