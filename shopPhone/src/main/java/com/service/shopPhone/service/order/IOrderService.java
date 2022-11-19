package com.service.shopPhone.service.order;

import java.util.UUID;

import org.springframework.data.domain.Pageable;

import com.service.shopPhone.models.Response;
import com.service.shopPhone.models.StatusResponseModel;
import com.service.shopPhone.models.order.CreateOrderRequestModel;
import com.service.shopPhone.models.order.GetAllOrderResponseModel;
import com.service.shopPhone.models.order.OrderDetailResponseModel;

public interface IOrderService {
    public Response<StatusResponseModel> createOrder(String username,CreateOrderRequestModel requestModel);
    public Response<GetAllOrderResponseModel> getAll(String searchText, String status, Pageable pageable);
    public Response<OrderDetailResponseModel> getDetail(UUID orderId);
}
